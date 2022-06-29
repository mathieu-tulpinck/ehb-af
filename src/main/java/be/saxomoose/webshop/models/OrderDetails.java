package be.saxomoose.webshop.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id")
    private Item item;

    private int quantity;

    private BigDecimal price;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
