package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>
{
}
