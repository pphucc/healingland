/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.payment;

import model.VnPayConfiguration;
import model.VnPaymentResponseModel;
import services.VnPayService;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FPT
 */
public class ExecutePayment extends HttpServlet {

    VnPayConfiguration config;
    VnPayService _vnPayService;

    @Override
    public void init() {
        config = new VnPayConfiguration();
        _vnPayService = new VnPayService(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("OK");

        VnPaymentResponseModel responseModel;

        try {
            responseModel = _vnPayService.paymentExecute(request.getParameterMap());
            if (responseModel.VnPayResponseCode.equals("00")) {
                System.out.println("OK");
                response.sendRedirect("../homestay/view_homestay/payment_result.jsp");
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(ExecutePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
