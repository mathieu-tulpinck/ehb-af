package be.saxomoose.webshop.controllers;

import be.saxomoose.webshop.models.Item;
import be.saxomoose.webshop.repositories.CategoryRepository;
import be.saxomoose.webshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class ItemController
{
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ItemController(CategoryRepository categoryRepository, ItemRepository itemRepository)
    {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    private String currentCategory;
    private Collection<Item> items;

    @GetMapping("/items")
    public ModelAndView index()
    {
        var categories = categoryRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categories);

        if (currentCategory.isEmpty()) {
            items = itemRepository.findAll();
            currentCategory = "All items";
            modelAndView.addObject("items", items);
        }

        return modelAndView;
    }
}
