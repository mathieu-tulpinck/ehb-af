package com.saxomoose.webshop.controllers;

import com.saxomoose.webshop.models.Item;
import com.saxomoose.webshop.repositories.CategoryRepository;
import com.saxomoose.webshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class ItemController
{
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private Long currentCategoryId;
    private Collection<Item> items;

    @Autowired
    public ItemController(CategoryRepository categoryRepository, ItemRepository itemRepository)
    {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String message)
    {
        var categories= categoryRepository.findAll();
        var modelAndView = new ModelAndView("items/index");
        modelAndView.addObject("categories", categories);

        if (categoryId == null) {
            items = itemRepository.findAll();
            modelAndView.addObject("items", items);
        } else {
            currentCategoryId = categoryId;
            items = itemRepository.findItemsByCategoryId(currentCategoryId);
            modelAndView.addObject("items", items);
        }

        if (message != null) {
            modelAndView.addObject("message", message);
        }

        return modelAndView;
    }

    @GetMapping("/items/details/{id}")
    public ModelAndView details(@PathVariable("id") Long id)
    {
        var item = itemRepository.findById(id);

        if (item.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var modelAndView = new ModelAndView("items/details");
        modelAndView.addObject("item", item.get());

        return modelAndView;
    }
}
