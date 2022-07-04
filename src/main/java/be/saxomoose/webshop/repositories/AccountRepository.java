package be.saxomoose.webshop.repositories;

import be.saxomoose.webshop.models.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>
{
    Account findByUsername(String username);
}
