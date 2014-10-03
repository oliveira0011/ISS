/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author Ivo
 */
public class UserDTO {
    private String email;
    private String name;    
    private long birthday;
    
    private long valueSaved;
    
    private String address;

    public UserDTO(String email, String name, long birthday, long valueSaved, String address) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.valueSaved = valueSaved;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getValueSaved() {
        return valueSaved;
    }

    public void setValueSaved(long valueSaved) {
        this.valueSaved = valueSaved;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
