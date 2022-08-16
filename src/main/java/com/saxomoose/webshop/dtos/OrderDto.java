package com.saxomoose.webshop.dtos;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDto
{
    @NotNull
    private String address;

    @NotNull
    @Column(name = "post_code")
    @Min(value = 1000)
    @Max(value = 9999)
    private Integer postalCode;

    @NotNull
    private String city;

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Integer getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }


}
