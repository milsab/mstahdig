/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.service.BuyerService;
import edu.iit.sat.itmd4515.msabouri.service.GroupService;
import edu.iit.sat.itmd4515.msabouri.service.SellerService;
import edu.iit.sat.itmd4515.msabouri.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
    
    @NotBlank(message = "You must enter your first name")
    private String firstName;
    @NotBlank(message = "You must enter your last name")
    private String lastName;
    
    private String birthday;
    private String gender;
    private String group;
    

    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;
//    @EJB
//    private BuyerService buyerSvc;
//    @EJB
//    private SellerService sellerService;

    /**
     *
     */
    public LoginController() {
    }

    //action methods

    /**
     *
     * @return
     */
    public String doLogin() {
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

    /**
     *
     * @return
     */
    public String doLogout() {
        try {
            HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
            req.logout();

        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return "/login?faces-redirect=true";
    }

    // helper methods for login process

    /**
     *
     * @return
     */
    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    /**
     *
     * @return
     */
    public boolean isBuyer() {
        return context.getExternalContext().isUserInRole("BUYER_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isSeller() {
        return context.getExternalContext().isUserInRole("SELLER_ROLE");
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public String getGroup() {
        return group;
    }

    /**
     *
     * @param group
     */
    public void setGroup(String group) {
        this.group = group;
    }

}