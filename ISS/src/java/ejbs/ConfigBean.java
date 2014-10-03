package ejbs;

import java.time.Instant;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    UserFacade userFacade;

    @EJB
    ProductFacade productFacade;
    
    @PostConstruct
    public void popularBD() {
        userFacade.addUser("user1@mail.pt", "pass", "User 1", Date.from(Instant.now()), 120, "Rua das Orquideas");
        userFacade.addUser("user2@mail.pt", "pass", "User 2", Date.from(Instant.now()), 120, "Rua das Rosas");
        
        productFacade.addProduct("Leite 100% desnatado", "Leite Mimosa", 0.56, -1, -1);
        productFacade.addProduct("Iogurte magro. 4 embalagens", "Iogurte Mimosa", 1.2, -1, -1);
        for (int i = 0; i < 30; i++) {
            productFacade.addProduct("Description product " + (i+1), "Product " + (i+1), 1.2, -1, -1);    
        }
        
    }
}
