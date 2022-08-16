package com.saxomoose.webshop.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private ApplicationUser user;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Collection<OrderDetails> orderDetails;

    @NotNull
    @DecimalMin(value = "0.00", message = "Subtotal must com positive")
    private BigDecimal subtotal;

    @NotNull
    @DecimalMin(value = "0.00", message = "Subtotal must com positive")
    private BigDecimal total;

    @NotNull
    private String address;

    @NotNull
    @Column(name = "post_code")
    @Min(value = 1000)
    @Max(value = 9999)
    private Integer postalCode;

    @NotNull
    private String city;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant lastModifiedDate;

    public Order()
    {
    }

    public Order(String address, Integer postalCode, String city)
    {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getUser()
    {
        return user;
    }

    public void setUser(ApplicationUser user)
    {
        this.user = user;
    }

    public Collection<OrderDetails> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetails> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public BigDecimal getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal)
    {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal()
    {
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

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

    public Instant getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate)
    {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }
}
