/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
public class DAO{
    public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://localhost:1433;databaseName=homestay_booking;encrypt=true;trustServerCertificate=true";
			String userName="sa";
			String password="123456";
			Connection connection = DriverManager.getConnection(url, userName, password);
			System.out.println("connect successfully");
			return connection;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static void main(String[] args) {
		getConnection();
	}

}