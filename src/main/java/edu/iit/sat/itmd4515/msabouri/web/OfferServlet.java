/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Offer;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.validation.Validator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

/**
 *
 * @author Milad
 */
@WebServlet(name = "OfferServlet", urlPatterns = {"/OfferServlet"})
public class OfferServlet extends HttpServlet {

    @Resource
    Validator validator;

    private static final Logger LOG = Logger.getLogger(OfferServlet.class.getName());

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
        LOG.info("Inside GET");
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
        try {
            LOG.info("Inside POST");
            LOG.info("Title: " + request.getParameter("title"));
            LOG.info("Description: " + request.getParameter("description"));
            LOG.info("Price: " + request.getParameter("price"));
            LOG.info("Quantity: " + request.getParameter("quantity"));


            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));

            Offer offer = new Offer(title, description, new Date(), new BigDecimal(price), quantity);
            LOG.info(offer.toString());

            Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

            if (constraintViolations.size() > 0) {
                LOG.info("Offer has a problem.  Failed validation");
                for (ConstraintViolation<Offer> bad : constraintViolations) {
                    LOG.info(bad.getPropertyPath() + " " + bad.getMessage());
                }

                request.setAttribute("offer", offer);
                request.setAttribute("errors", constraintViolations);
                
//                response.sendRedirect("error.html");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/newOfferForm.jsp");
                dispatcher.forward(request, response);

            } else {
                LOG.info("Validation Complete. No problems. Proceed.");
                request.setAttribute("offer", offer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirm.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception /*ParseException*/ ex) {
            Logger.getLogger(OfferServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
