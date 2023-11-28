package com.udPlatform;

import java.io.Serializable;

public class Address implements Serializable {

    private String street;
    private String city;
    private String state;
    private int zipCode;

    public Address(String street, String city, String state, int zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString(){
        String space="          ";
        return String.format(" { \n %s Street:%s \n %s City:%s \n %s State:%s \n %s Zip: %d \n%s}",
                space,street,space,city,space,state,space,zipCode,space);

    }
}
