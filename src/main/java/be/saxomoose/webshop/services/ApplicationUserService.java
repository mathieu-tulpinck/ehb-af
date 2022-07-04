package be.saxomoose.webshop.services;

import be.saxomoose.webshop.models.ApplicationUser;
import be.saxomoose.webshop.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService
{
    private ApplicationUserRepository applicationUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository accountRepository, PasswordEncoder passwordEncoder)
    {
        this.applicationUserRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Load the user from the users table by username. If not found, throw UsernameNotFoundException.
    // 2. Convert/wrap the user to a UserDetails object and return it.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        var user = applicationUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public ApplicationUser createNew(ApplicationUser account) {
        encodePassword(passwordEncoder, account);
        var createdAccount = this.applicationUserRepository.save(account);
        return createdAccount;
    }

    private void encodePassword(PasswordEncoder passwordEncoder, ApplicationUser account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
