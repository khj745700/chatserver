package com.example.chatserver.repository;

import com.example.chatserver.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Flux<User> findAll();

}
