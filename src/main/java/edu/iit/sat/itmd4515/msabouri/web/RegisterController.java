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
    
    public RegisterController(){
        
    }
    
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
    public void sendEmail(){
        EmailService emailSvc = new EmailService(email, username, password, firstName, lastName);
        Thread th = new Thread(emailSvc);
        LOG.info("Starting a new thread to send the email..");
        th.start();
        LOG.info("The email was sent successfully");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
