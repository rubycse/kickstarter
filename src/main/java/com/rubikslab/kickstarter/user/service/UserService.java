package com.rubikslab.kickstarter.user.service;

import com.rubikslab.kickstarter.user.domain.User;
import com.rubikslab.kickstarter.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author lutfun
 * @since 10/29/18
 */

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return user;
    }

    public boolean isUserExistsWithUsername(String username) {
        return userRepository.countUserByUsername(username) > 0;
    }

    public boolean isUserExistsWithEmail(String email) {
        return userRepository.countUserByEmail(email) > 0;
    }
}
