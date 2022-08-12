//package com.saxomoose.webshop.data;
//
//import com.saxomoose.webshop.enums.Role;
//import com.saxomoose.webshop.models.ApplicationUser;
//import com.saxomoose.webshop.services.ApplicationUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SeedDatabase implements ApplicationRunner
//{
//    private final ApplicationUserService applicationUserService;
//
//    @Autowired
//    public SeedDatabase(ApplicationUserService applicationUserService)
//    {
//        this.applicationUserService = applicationUserService;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception
//    {
//        ApplicationUser user = new ApplicationUser();
//        user.setUsername("mathieu@webshop.test");
//        user.setPassword("password");
//        user.setEnabled(true);
//        user.setRole("ROLE_ADMIN");
//        applicationUserService.createNew(user);
//
//    }
//}
