/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Milad
 */
@Named
@RequestScoped
public class LoginController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    
    @NotBlank(message = "You must enter a Username")
    private String username;
    @NotBlank(message = "You must enter a Password")
    private String password;

    public LoginController() {
    }
    
    //action methods
    public String doLogin(){
        try {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            req.login(username, username);
            return "/welcome?faces-redirect=true";
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Faild", "Please check your username or password and try again."));
            return "/login";
        }
    }
    
    public String doLogout(){
        try {
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            req.logout();

        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return "/login?faces-redirect=true";
    }
    
    
    // helper methods for login process
    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    public boolean isBuyer() {
        return context.getExternalContext().isUserInRole("BUYER_ROLE");
    }

    public boolean isSeller() {
        return context.getExternalContext().isUserInRole("SELLER_ROLE");
    }

    public boolean isAdmin() {
        return context.getExternalContext().isUserInRole("ADMIN_ROLE");
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
