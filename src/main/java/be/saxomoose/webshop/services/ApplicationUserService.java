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

    public ApplicationUser findByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }

    public ApplicationUser createNew(ApplicationUser user) {
        encodePassword(passwordEncoder, user);
        var createdUser = this.applicationUserRepository.save(user);
        return createdUser;
    }

    public void activate(ApplicationUser user) {
        user.setEnabled(true);
        this.applicationUserRepository.save(user);
    }

    private void encodePassword(PasswordEncoder passwordEncoder, ApplicationUser account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
