package be.saxomoose.webshop.services;

import be.saxomoose.webshop.models.Account;
import be.saxomoose.webshop.models.ApplicationUser;
import be.saxomoose.webshop.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService
{
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder)
    {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Load the user from the users table by username. If not found, throw UsernameNotFoundException.
    // 2. Convert/wrap the user to a UserDetails object and return it.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return new ApplicationUser(account);
    }

    public Account createNew(Account account) {
        encodePassword(passwordEncoder, account);
        return this.accountRepository.save(account);
    }

    private void encodePassword(PasswordEncoder passwordEncoder, Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
