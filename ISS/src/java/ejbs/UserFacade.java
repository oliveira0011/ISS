package ejbs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dto.FuncionarioDTO;
import dto.LojaDTO;
import dto.UserDTO;
import entities.User;
import java.util.Date;

@Stateless
public class UserFacade {

    @PersistenceContext(unitName = "ISS")
    EntityManager em;

    public String hashPassword(String password){
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getNameByEmail(String email){
        User user = (User) em.createNamedQuery("User.findByEmail")
                .setParameter("email", email)
                .getSingleResult();
        return user.getName();
    }
    
    public UserDTO getUserByEmail(String email){
        User user = (User) em.createNamedQuery("User.findByEmail")
                .setParameter("email", email)
                .getSingleResult();
        
        return makeDTO(user);
    }
    
    
    public void addUser(String email, String password, String name, Date birthday, long valueSaved, String address){
        this.em.persist(new User(email, hashPassword(password), name, birthday, valueSaved, address));
        this.em.flush();
    }
    
    public UserDTO makeDTO(User u){
        return new UserDTO(u.getEmail(), u.getName(), u.getBirthday().getTime(), u.getValueSaved(), u.getAddress());
    }
}