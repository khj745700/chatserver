package com.example.chatserver.repository;

import com.example.chatserver.domain.Chat;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    Flux<Chat> findAllByCreateDateBefore(LocalDateTime createDate);


    @Tailable
    @Query("{user: ?0}") // Qyery : Mongodb의 Finder Query를 사용하는 것, 자리 표시자 방식으로 표현된 ?0, ?1 로 파라미터를 주입해줌.
    Flux<Chat> mFindByUser(String user);
}
