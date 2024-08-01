package com.ing.repository;

import com.ing.domain.Product;
import com.ing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
