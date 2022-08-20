package com.saxomoose.webshop.services;

import com.saxomoose.webshop.models.ApplicationUser;
import com.saxomoose.webshop.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService
{
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository accountRepository, PasswordEncoder passwordEncoder)
    {
        this.applicationUserRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    public void createNew(ApplicationUser user) {
        encodePassword(passwordEncoder, user);

        this.applicationUserRepository.save(user);
    }

    public void activate(ApplicationUser user) {
        user.setEnabled(true);
        this.applicationUserRepository.save(user);
    }

    private void encodePassword(PasswordEncoder passwordEncoder, ApplicationUser account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
