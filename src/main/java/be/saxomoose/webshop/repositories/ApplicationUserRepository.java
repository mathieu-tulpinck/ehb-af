package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>
{
    ApplicationUser findByUsername(String username);
}
