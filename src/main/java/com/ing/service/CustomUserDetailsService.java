package com.ing.service;

import com.ing.domain.User;
import com.ing.error.CustomException;
import com.ing.error.ErrorCode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username).orElseThrow(
                () -> {
                    logger.warn("User not found: {}", username);
                    return new CustomException(ErrorCode.USER_NOT_FOUND.getCode(),
                            MessageFormat.format(ErrorCode.USER_NOT_FOUND.getMessage(), username));
                });

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return entityManager.unwrap(Session.class)
                .bySimpleNaturalId(User.class)
                .loadOptional(username);
    }
}

