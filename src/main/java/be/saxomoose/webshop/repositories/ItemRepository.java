package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>
{
    List<Item> findItemsByCategoryId(Long categoryId);
}
