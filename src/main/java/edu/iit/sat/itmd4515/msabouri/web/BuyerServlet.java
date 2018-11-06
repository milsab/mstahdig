/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Buyer;
import edu.iit.sat.itmd4515.msabouri.domain.OrderFood;
import edu.iit.sat.itmd4515.msabouri.service.BuyerService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Milad
 */
@WebServlet(name = "BuyerServlet", urlPatterns = {"/buyer"})
public class BuyerServlet extends HttpServlet {
    
    @EJB
    BuyerService buyerSvc;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuyerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyerServlet at " + request.getContextPath() + "</h1>");
            
            out.println("<h2>Welcome " + request.getRemoteUser() + "</h2>");
            out.println("<a href=\"" + request.getContextPath() + "/logout\">Logout</a>");

            if (request.isUserInRole("BUYER_ROLE")) {
                Buyer buyer = buyerSvc.findByUserName(request.getRemoteUser());
                out.println("<h2>" + buyer.getUser().getUserName() + "  Full Name is: " + buyer.getFirstName()+ " " + buyer.getLastName() + "</h2>");
                out.println("<h2>" + buyer.getUser().getUserName() + "  Birthday is: " + buyer.getBirthday() + "</h2>");
                out.println("<h2>" + buyer.getUser().getUserName() + "  Gender is: " + buyer.getGender()+ "</h2>");
                
                out.println("<h2>" + buyer.getUser().getUserName() + "Orders List Price is:" + "</h2>");
                out.println("<ul>");
                for (OrderFood order : buyer.getOrders()) {
                    out.println("<li>" + order.getPrice() + "</li>");
                }
                out.println("</ul>");
                
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
