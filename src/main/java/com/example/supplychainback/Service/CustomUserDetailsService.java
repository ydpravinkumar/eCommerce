package com.example.supplychainback.Service;

import com.example.supplychainback.model.Dao.LocalUserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.supplychainback.model.LocalUser;

/**
 * Class which implements the UserDetailsService and searches for the user if not found throws an exception
 * Once user found, assigns the authorities as USER
 * Was having trouble figuring out how to store the JWT toen once the user logs in so that I can use this token to make sure
 * The application knows the user is logged in, so had to get a classmate's help for the same
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LocalUserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LocalUser localUser = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(localUser.getUsername())
                .password(localUser.getPassword())
                .authorities("USER")
                .build();
    }
}
