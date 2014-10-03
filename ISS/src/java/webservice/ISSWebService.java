package webservice;

import dto.ProductDTO;
import dto.UserDTO;
import ejbs.ProductFacade;
import ejbs.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "ISSWebService")
@HandlerChain(file = "ISSWebService_handler.xml")
public class ISSWebService {
    @EJB
    UserFacade userFacade;
    
    @EJB
    ProductFacade productFacade;
    
    @WebMethod(operationName = "getUserName")
    public String getName(@WebParam(name = "email") String email) {
        return userFacade.getNameByEmail(email);
    }
    
    @WebMethod(operationName = "getUser")
    public UserDTO getUser(@WebParam(name = "email") String email) {
        return userFacade.getUserByEmail(email);
    }
    
    @WebMethod(operationName = "getProducts")
    public List<ProductDTO> getProducts() {
        return productFacade.getAllProducts();
    }
}
