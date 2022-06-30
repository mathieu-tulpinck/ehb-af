package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>
{

}
