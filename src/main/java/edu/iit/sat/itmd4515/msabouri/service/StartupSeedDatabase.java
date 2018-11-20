package edu.iit.sat.itmd4515.msabouri.service;

import edu.iit.sat.itmd4515.msabouri.domain.security.Group;
import edu.iit.sat.itmd4515.msabouri.domain.security.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Milad
 */
@Startup
@Singleton
public class StartupSeedDatabase {
    @EJB private UserService userSvc;
    @EJB private GroupService groupSvc;
    
    @PostConstruct
    private void seedDatabase(){
        Group adminGroup = new Group("ADMINS", "admin of the system");
        User admin = new User("admin", "admin", true);
        admin.addGroup(adminGroup);
        
        groupSvc.create(adminGroup);
        userSvc.create(admin);
    }
}
