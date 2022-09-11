package com.saxomoose.webshop.repositories;

import com.saxomoose.webshop.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long>
{
    List<ShoppingCartItem> findItemsByShoppingCartId(UUID shoppingCartId);
    ShoppingCartItem findShoppingCartItemByShoppingCartIdAndItemId(UUID shoppingCartId, Long itemId);

    @Transactional
    void deleteAllByShoppingCartId(UUID shoppingCartId);

    // Offloads sum computations to database.
    @Query(value = "SELECT SUM(i.price * sci.quantity) AS subtotal FROM shopping_cart_items sci INNER JOIN items i ON sci.item_id = i.id WHERE sci.shopping_cart_id = :shoppingCartId", nativeQuery = true)
    BigDecimal getSubtotal(@Param("shoppingCartId") String shoppingCartId);
    @Query(value = "SELECT ROUND(SUM((i.price*(1 + i.vat_rate/100)) * sci.quantity),2) AS total FROM shopping_cart_items sci INNER JOIN items i ON sci.item_id = i.id WHERE sci.shopping_cart_id = :shoppingCartId", nativeQuery = true)
    BigDecimal getTotal(@Param("shoppingCartId") String shoppingCartId);

}
