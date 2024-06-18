/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.search;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


/**
 *
 * @author FPT
 */
public class ViewHomestay extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println(request.getParameter("homestay_id"));
//        request.getRequestDispatcher("")
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
