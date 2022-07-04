package be.saxomoose.webshop.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
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
    @DecimalMin(value = "0.00", message = "Subtotal must be positive")
    private BigDecimal subtotal;

    @NotNull
    @DecimalMin(value = "0.00", message = "Subtotal must be positive")
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getApplicationUser()
    {
        return user;
    }

    public void setApplicationUser(ApplicationUser user)
    {
        this.user = user;
    }
}
