package com.mangaworld.animeworld.security;

import com.mangaworld.animeworld.data.UserRepository;
import com.mangaworld.animeworld.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
       /** User user = new User("foo@foo.com", "foo", passwordEncoder.encode("password"),
                             "John", "Adam");
        user.setRoles(Set.of(User.Role.ROLE_USER));
        user.setEnabled(true);
        userRepo.save(user); **/

    }
}
