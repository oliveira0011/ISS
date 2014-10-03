package webservice;

import entities.User;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import utils.Utils;

public class ISSWebServiceHandler implements SOAPHandler<SOAPMessageContext> {
    
    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ISSWebServiceHandler.class.getName());
    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        Map http_headers = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("email");
        List tokenList = (List) http_headers.get("token");
        if (userList != null && tokenList != null) {
            String email = Utils.rot13(userList.get(0).toString());
            String token = tokenList.get(0).toString();
            try {
                User u = this.em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getSingleResult();
                if (u != null) 
                    return token.equals(Utils.hashString(Utils.rot13(u.getEmail() + u.getPassword())));
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
        }
        return false;
    }

    

    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}