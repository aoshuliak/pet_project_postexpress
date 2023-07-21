package com.postexpress.Postrexpress.repository;

import com.postexpress.Postrexpress.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where email =?1", nativeQuery = true)
    User getUserByEmail(String email);

    @Query(value = "select * from users where email =?1", nativeQuery = true)
    Optional<User> getByUserEmail(String email);
}