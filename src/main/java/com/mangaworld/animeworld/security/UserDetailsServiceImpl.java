package com.mangaworld.animeworld.security;

import com.mangaworld.animeworld.data.UserRepository;
import com.mangaworld.animeworld.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //User user = userRepo.findByUsername(username);
        User user = userRepo.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("Username " + email + " not found");
        return user;
    }
}
