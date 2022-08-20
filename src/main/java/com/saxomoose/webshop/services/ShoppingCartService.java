package com.saxomoose.webshop.services;

import com.saxomoose.webshop.models.Item;
import com.saxomoose.webshop.models.ShoppingCartItem;
import com.saxomoose.webshop.repositories.ItemRepository;
import com.saxomoose.webshop.repositories.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

// Annotation makes class singleton by default.
@Service
public class ShoppingCartService
{
    private static UUID shoppingCartId;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ItemRepository itemRepository;
    private final HttpSession session;

    @Autowired
    public ShoppingCartService(ShoppingCartItemRepository shoppingCartItemRepository, ItemRepository itemRepository, HttpSession session)
    {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.itemRepository = itemRepository;
        this.session = session;
    }

    public List<ShoppingCartItem> getShoppingCartItems()
    {
        initializeCart();

        return shoppingCartItemRepository.findItemsByShoppingCartId(shoppingCartId);
    }

    public void addToShoppingCart(Item item)
    {
        initializeCart();
        var shoppingCartItem = shoppingCartItemRepository.findShoppingCartItemByShoppingCartIdAndItemId(shoppingCartId, item.getId());
        if (shoppingCartItem == null) {
            shoppingCartItemRepository.save(new ShoppingCartItem(item, shoppingCartId, 1, Instant.now(), Instant.now()));
        } else {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity()+1);
            shoppingCartItemRepository.save(shoppingCartItem);
        }
        if (item.getQuantityInStock() > 0) {
            item.setQuantityInStock(item.getQuantityInStock() - 1);
            itemRepository.save(item);
        }
    }

    public void removeFromCart(ShoppingCartItem shoppingCartItem)
    {
        var quantity = shoppingCartItem.getQuantity();
        if (quantity == 1) {
            shoppingCartItemRepository.delete(shoppingCartItem);
        } else if (quantity > 1) {
            shoppingCartItem.setQuantity(quantity - 1);
            shoppingCartItemRepository.save(shoppingCartItem);
        }

        var item = shoppingCartItem.getItem();
        item.setQuantityInStock(item.getQuantityInStock() + 1);
        itemRepository.save(item);
    }

    public BigDecimal getTotal()
    {
        return shoppingCartItemRepository.getTotal(shoppingCartId.toString());
    }

    public BigDecimal getSubtotal()
    {
        return shoppingCartItemRepository.getSubtotal(shoppingCartId.toString());
    }

    // Performs a batch delete of all shopping cart items.
    public void clearShoppingCart()
    {
        shoppingCartItemRepository.deleteAllByShoppingCartId(shoppingCartId);
    }

    // Does not persist shopping cart across sessions.
    private void initializeCart()
    {
        UUID cartId;
        if (session.getAttribute("CartId") != null) {
            cartId = UUID.fromString(session.getAttribute("CartId").toString());
        } else {
            cartId = UUID.randomUUID();
            session.setAttribute("CartId", cartId.toString());
        }

        shoppingCartId = cartId;
    }
}
