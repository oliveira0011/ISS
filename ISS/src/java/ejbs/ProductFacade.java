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
import dto.ProductDTO;
import dto.UserDTO;
import entities.Product;
import entities.User;

@Stateless
public class ProductFacade {

    @PersistenceContext(unitName = "ISS")
    EntityManager em;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = em.createNamedQuery("Product.findAll")
                .getResultList();
        List<ProductDTO> productsDTO = new ArrayList<>();
        for (Product product : products) {
            productsDTO.add(makeDTO(product));
        }
        return productsDTO;
    }

    public void addProduct(String description, String name, double value, long x, long y) {
        this.em.persist(new Product(description, name, value, x, y));
        this.em.flush();
    }

    public ProductDTO makeDTO(Product p) {
        return new ProductDTO(p.getDescription(), p.getId(), p.getName(), p.getValue(), p.getX(), p.getY());
    }
}
