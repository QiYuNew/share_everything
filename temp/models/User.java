package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.*;

public class User extends Person {
    private boolean useragreement = false;

    public User() {
        super();
    }

    public User(int id, String firstname, String lastname){
        super.setId(id);
        super.setFirstname(firstname);
        super.setLastname(lastname);
    }

    public boolean isUseragreement() {
        return useragreement;
    }

    public void setUseragreement(boolean useragreement) {
        this.useragreement = useragreement;
    }
}
