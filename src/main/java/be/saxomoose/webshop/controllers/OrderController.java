package be.saxomoose.webshop.controllers;

import be.saxomoose.webshop.dtos.OrderDto;
import be.saxomoose.webshop.models.Order;
import be.saxomoose.webshop.services.OrderService;
import be.saxomoose.webshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class OrderController
{
    private final OrderService orderService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService)
    {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/order")
    public ModelAndView order()
    {
        var modelAndView = new ModelAndView();
        var orderDto = new OrderDto();
        modelAndView.addObject("orderDto", orderDto);
        modelAndView.setViewName("orders/form");

        return modelAndView;
    }

    @PostMapping("/order")
    public ModelAndView order(@Valid @ModelAttribute("orderDto") OrderDto orderDto, BindingResult bindingResult)
    {
        var modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("BindingResult", bindingResult);
            modelAndView.setViewName("orders/form");
        } else {
            var order = new Order(orderDto.getAddress(), orderDto.getPostalCode(), orderDto.getCity());
            orderService.complete(order);
            shoppingCartService.clearShoppingCart();
            try {
                orderService.send(order);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            modelAndView.addObject("message", "Order confirmation sent to your mailbox");
            modelAndView.setViewName("redirect:/");
        }

        // send email with order confirmation
        // redirect to homepage with message

        return modelAndView;
    }
}
