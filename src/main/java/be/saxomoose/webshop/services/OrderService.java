package be.saxomoose.webshop.services;

import be.saxomoose.webshop.dtos.OrderDto;
import be.saxomoose.webshop.models.ApplicationUser;
import be.saxomoose.webshop.models.Order;
import be.saxomoose.webshop.models.OrderDetails;
import be.saxomoose.webshop.repositories.OrderDetailsRepository;
import be.saxomoose.webshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderService
{
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ShoppingCartService shoppingCartService)
    {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.shoppingCartService = shoppingCartService;
    }

    public void complete(Order order)
    {
        var shoppingCartItems = shoppingCartService.getShoppingCartItems();
        var orderDetailsList = shoppingCartItems
                .stream()
                .map(shoppingCartItem -> new OrderDetails(order, shoppingCartItem.getItem(), shoppingCartItem.getQuantity(), shoppingCartItem.getItem().getPrice()))
                .collect(toList());
        orderDetailsRepository.saveAll(orderDetailsList);
        order.setUser((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        order.setSubtotal(shoppingCartService.getSubtotal());
        order.setTotal(shoppingCartService.getTotal());
        order.setCreatedDate(Instant.now());
        order.setLastModifiedDate(Instant.now());
        orderRepository.save(order);
    }

    public void send(Order order)
    {
    }
}
