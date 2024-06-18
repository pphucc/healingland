package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import model.Booking;

public class BookingDAO extends DAO {

    public static ArrayList<Booking> getAllUnapprovedBookingsOfHomestay(int homestay_id) {
        try ( Connection con = getConnection()) {
            PreparedStatement stmt = con.prepareStatement("select * from tblBooking where ht_id=? AND booking_status=0");
            stmt.setInt(1, homestay_id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                bookings.add(new Booking(rs.getInt("booking_id"), AccountDAO.getBasicInforOfAccount(rs.getInt("customer_id")),
                        rs.getInt("guest_count"), rs.getDate("date_booked"), rs.getDate("date_checkin"),
                        rs.getDate("date_checkout"), RoomDAO.getRoomBookingBasicInfor(rs.getInt("booking_id")),
                        rs.getInt("booking_status")));
            }
            Collections.sort(bookings, (i, j) -> i.getDate_booked().compareTo(j.getDate_booked()));
            return bookings;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean changeStatusBooking(int booking_id, int status) {
        try ( Connection con = getConnection()) {
            PreparedStatement stmt = con.prepareStatement("update tblBooking set booking_status=? where booking_id=?");
            stmt.setInt(1, status);
            stmt.setInt(2, booking_id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static Booking getBookingByHt_idAndAccount_id(int ht_id, int account_id) {
        try ( Connection con = getConnection()) {
            PreparedStatement stmt = con.prepareStatement("select * from tblBooking where ht_id = ? and customer_id = ?");
            stmt.setInt(1, ht_id);
            stmt.setInt(2, account_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBooking_id(rs.getInt("booking_id"));
                booking.setHt_id(rs.getInt("ht_id"));
                return booking;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Booking getBookingById(int booking_id) {
        try ( Connection con = getConnection()) {
            PreparedStatement stmt = con.prepareStatement("select * from tblBooking where booking_id = ?");
            stmt.setInt(1, booking_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBooking_id(rs.getInt("booking_id"));
                booking.setHt_id(rs.getInt("ht_id"));
                return booking;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
