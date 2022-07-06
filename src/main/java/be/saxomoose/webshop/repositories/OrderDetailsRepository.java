package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>
{
}
