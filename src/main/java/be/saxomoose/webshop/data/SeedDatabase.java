package be.saxomoose.webshop.data;

import be.saxomoose.webshop.enums.Role;
import be.saxomoose.webshop.models.Account;
import be.saxomoose.webshop.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDatabase implements ApplicationRunner
{
    private final AccountService accountService;

    @Autowired
    public SeedDatabase(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        Account testAccount = new Account();
        testAccount.setUsername("mathieu@webshop.test");
        testAccount.setPassword("password");
        testAccount.setRole(Role.ADMIN);

        accountService.createNew(testAccount);
    }
}
