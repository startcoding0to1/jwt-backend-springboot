package com.startcoding0to1.backend.repository;

import com.startcoding0to1.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    public Optional<User> findByUserName(String username);
}
