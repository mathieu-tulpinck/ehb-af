package be.saxomoose.webshop.controllers;

import be.saxomoose.webshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryRepository categoryRepository;
}
