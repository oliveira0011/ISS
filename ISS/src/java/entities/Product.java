/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Products")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.name"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 938257838307712726L;
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String description;
    @NotNull
    private String name;
    
    @NotNull
    private double value;
    
    private long x;
    private long y;
    
    public Product() {
        x = -1;
        y = -1;
    }
    
    public Product(String description, String name, double value) {
        this();
        this.description = description;
        this.name = name;
        this.value = value;
    }

    public Product(String description, String name, double value, long x, long y) {
        this.description = description;
        this.name = name;
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

}

