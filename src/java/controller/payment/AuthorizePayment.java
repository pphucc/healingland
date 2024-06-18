package controller.payment;

import model.VnPayConfiguration;
import model.VnPaymentRequestModel;
import services.VnPayService;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.time.LocalDateTime;

/**
 *
 * @author FPT
 */
public class AuthorizePayment extends HttpServlet {

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

        try {

            VnPaymentRequestModel requestModel = new VnPaymentRequestModel();
            requestModel.setAmount(100000);
            requestModel.setCreatedDate(LocalDateTime.now());
            requestModel.setDescription("Test Payment");
            requestModel.setFullName("Dinh Phuc");
            requestModel.setOrderId(String.valueOf((int)(Math.random() * 9000) + 1000));

            response.sendRedirect(_vnPayService.createPaymentUrl(request, requestModel));
//            response.sendRedirect("https://facebook.com/pphucc");

        } catch (Exception ex) {

        }
    }
}
