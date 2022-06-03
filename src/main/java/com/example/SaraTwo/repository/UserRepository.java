package com.example.SaraTwo.repository;

import com.example.SaraTwo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
