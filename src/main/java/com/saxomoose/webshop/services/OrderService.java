package com.saxomoose.webshop.services;

import com.saxomoose.webshop.models.ApplicationUser;
import com.saxomoose.webshop.models.Order;
import com.saxomoose.webshop.models.OrderDetails;
import com.saxomoose.webshop.repositories.OrderDetailsRepository;
import com.saxomoose.webshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Instant;

import static java.util.stream.Collectors.toList;

@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderService
{
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ShoppingCartService shoppingCartService;
    private final ApplicationUserService applicationUserService;
    private final HtmlEmailService emailService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ShoppingCartService shoppingCartService, ApplicationUserService applicationUserService, HtmlEmailService emailService)
    {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.shoppingCartService = shoppingCartService;
        this.applicationUserService = applicationUserService;
        this.emailService = emailService;
    }

    public void complete(Order order)
    {
        var user = (ApplicationUser)applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        order.setUser(user);
        order.setSubtotal(shoppingCartService.getSubtotal());
        order.setTotal(shoppingCartService.getTotal());
        order.setCreatedDate(Instant.now());
        order.setLastModifiedDate(Instant.now());
        orderRepository.save(order);
        var shoppingCartItems = shoppingCartService.getShoppingCartItems();
        var orderDetailsList = shoppingCartItems
                .stream()
                .map(shoppingCartItem -> new OrderDetails(order, shoppingCartItem.getItem(), shoppingCartItem.getQuantity(), shoppingCartItem.getItem().getPrice()))
                .collect(toList());
        orderDetailsRepository.saveAll(orderDetailsList);

    }

    public void send(Order order) throws MessagingException
    {
        var user = SecurityContextHolder.getContext().getAuthentication().getName();
        emailService.sendHtmlEmail(user, "info@webshop.test", "Order confirmation", LocaleContextHolder.getLocale(), order);
    }
}
