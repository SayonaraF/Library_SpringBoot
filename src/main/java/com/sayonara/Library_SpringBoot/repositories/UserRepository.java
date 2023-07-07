package com.sayonara.Library_SpringBoot.repositories;

import com.sayonara.Library_SpringBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findUsersByRole(String role);
}
