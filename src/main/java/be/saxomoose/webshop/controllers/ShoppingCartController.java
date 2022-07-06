package be.saxomoose.webshop.controllers;

import be.saxomoose.webshop.repositories.ItemRepository;
import be.saxomoose.webshop.repositories.ShoppingCartItemRepository;
import be.saxomoose.webshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ShoppingCartController
{
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartItemRepository shoppingCartItemRepository, ItemRepository itemRepository)
    {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/shoppingcart")
    public ModelAndView index(@RequestParam(required = false) String message)
    {
        var items = shoppingCartService.getShoppingCartItems();
        var modelAndView = new ModelAndView();
        modelAndView.addObject("shoppingCartItems", items);
        if (message != null) {
            modelAndView.addObject("message", message);
        }
        if (!items.isEmpty()) {
            var subtotal = shoppingCartService.getSubtotal();
            var total = shoppingCartService.getTotal();
            modelAndView.addObject("subtotal", subtotal);
            modelAndView.addObject("total", total);
        }

        modelAndView.setViewName("cart/index");

        return modelAndView;
    }

    @GetMapping("/shoppingcart/{itemId}")
    public RedirectView addToCart(@PathVariable("itemId") Long itemId, RedirectAttributes attributes)
    {
        var item = itemRepository.findById(itemId);
        if (item.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        shoppingCartService.addToShoppingCart(item.get());
        attributes.addAttribute("message", "Item added to shopping cart");

        return new RedirectView("/shoppingcart");
    }

    @PostMapping("/shoppingcart/{shoppingCartItemId}")
    public RedirectView removeFromCart(@PathVariable("shoppingCartItemId") Long shoppingCartItemId, RedirectAttributes attributes)
    {
        var shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId);
        if (shoppingCartItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        shoppingCartService.removeFromCart(shoppingCartItem.get());
        attributes.addAttribute("message", "Item removed from shopping Cart");

        return new RedirectView("/shoppingcart");
    }
}
