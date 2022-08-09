package com.saxomoose.webshop.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0.00", message = "*Price must com positive")
    private BigDecimal price;

    // Divide by 100 on use.
    @NotNull
    @Column(name = "vat_rate", columnDefinition = "integer default 21")
    @Min(value = 0, message = "VAT rate must com positive")
    private Integer vatRate = 21;

    @NotNull
    @Column(name = "quantity_in_stock")
    @Min(value = 0, message = "Amount in stock must com positive")
    private Integer quantityInStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVatRate() {
        return vatRate;
    }

    public void setVatRate(Integer vatRate) {
        this.vatRate = vatRate;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
