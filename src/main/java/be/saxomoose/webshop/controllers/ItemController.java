package be.saxomoose.webshop.controllers;

import be.saxomoose.webshop.models.Item;
import be.saxomoose.webshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController
{
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String index()
    {
//        var items = itemRepository.findAll();
        return "index";
    }


}
