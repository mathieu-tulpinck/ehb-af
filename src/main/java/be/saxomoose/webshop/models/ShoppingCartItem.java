package be.saxomoose.webshop.models;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Item item;

    @NotNull
    // No unique constraint given transient nature. What matters is that there is no collision between current sessions.
    @Column(name = "shopping_cart_id"/*, columnDefinition = "varchar(36)"*/)
    @Type(type="org.hibernate.type.UUIDCharType")
//    @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
    private UUID shoppingCartId;

    @NotNull
    private int quantity;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant lastModifiedDate;

    public ShoppingCartItem() { }
    public ShoppingCartItem(Item item, UUID shoppingCartId, int quantity, Instant createdDate, Instant lastModifiedDate)
    {
        this.item = item;
        this.shoppingCartId = shoppingCartId;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public UUID getShoppingCartId()
    {
        return shoppingCartId;
    }

    public void setShoppingCartId(UUID shoppingCardId)
    {
        this.shoppingCartId = shoppingCardId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


}
