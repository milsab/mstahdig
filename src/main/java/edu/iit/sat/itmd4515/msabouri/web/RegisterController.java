package edu.iit.sat.itmd4515.msabouri.web;

import edu.iit.sat.itmd4515.msabouri.domain.Buyer;
import edu.iit.sat.itmd4515.msabouri.domain.Seller;
import edu.iit.sat.itmd4515.msabouri.domain.security.Group;
import edu.iit.sat.itmd4515.msabouri.domain.security.User;
import edu.iit.sat.itmd4515.msabouri.service.BuyerService;
import edu.iit.sat.itmd4515.msabouri.service.EmailService;
import edu.iit.sat.itmd4515.msabouri.service.GroupService;
import edu.iit.sat.itmd4515.msabouri.service.SellerService;
import edu.iit.sat.itmd4515.msabouri.service.UserService;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Milad
 */

@Named
@RequestScoped
public class RegisterController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());
    @NotBlank(message = "You must enter a Username")
    private String username;
    @NotBlank(message = "You must enter a Password")
    private String password;
    
    @NotBlank(message = "You must enter your First Name")
    private String firstName;
    @NotBlank(message = "You must enter your Last Name")
    private String lastName;
    
    
    private Date birthday;
    
    
    @NotBlank(message = "You must enter your Email")
    private String email;
    private String gender;
    private String group;
    

    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;
    @EJB
    private BuyerService buyerSvc;
    @EJB
    private SellerService sellerService;
    
    /**
     *
     */
    public RegisterController(){
        
    }
    
    /**
     *
     * @param registerController
     * @return The page address that we need to redirect to it.
     */
    public String doSignUp(RegisterController registerController) {
        Group group = groupSvc.findByGroupName(this.group);
        User newUser = new User(username, password, true);
        newUser.addGroup(group);
        userSvc.create(newUser);
        
        if(group.getGroupName().equals("BUYER")){
            Buyer buyer = new Buyer(firstName, lastName, gender, birthday, email);
            buyer.setUser(newUser);
            buyerSvc.create(buyer);
        } else if (group.getGroupName().equals("SELLER")){
            Seller seller = new Seller(firstName, lastName, gender, birthday, email);
            seller.setUser(newUser);
            sellerService.create(seller);
        }
        sendEmail();
        return "/login?faces-redirect=true";
    }
    
    //Making a seprate thread for sending the email notification

    /**
     * Sending the WELCOMMING email to the user
     */
    public void sendEmail(){
        EmailService emailSvc = new EmailService(email, username, password, firstName, lastName);
        //send email in a different thread
        Thread th = new Thread(emailSvc);
        LOG.info("Starting a new thread to send the email..");
        th.start();
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
    public Date getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
