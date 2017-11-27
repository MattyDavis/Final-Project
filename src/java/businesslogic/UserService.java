package businesslogic;

import dataaccess.UserDB;
import domainmodel.Company;
import domainmodel.Role;
import domainmodel.User;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUser(username);
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }
    
    public List<User> getCompany(Company company) throws Exception
    {
        return userDB.getCompanyUsers(company);
    }

    public int update(String username, String password, String email, boolean active, String firstname, String lastname,Company company) throws Exception {
        User user = userDB.getUser(username);
        user.setCompany(company);
        user.setPassword(password);
        user.setActive(active);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String email, boolean active, String firstname, String lastname,Company company) throws Exception {
        
        User user = new User(username, password, email, active, firstname, lastname);
        user.setCompany(company);
        Role role = new Role(2);
        user.setRole(role);
        return userDB.insert(user);
    }
}
