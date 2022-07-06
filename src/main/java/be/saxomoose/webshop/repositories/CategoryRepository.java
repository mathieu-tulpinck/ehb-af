package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
