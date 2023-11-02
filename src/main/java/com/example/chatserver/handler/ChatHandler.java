package com.example.chatserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.logging.Level;

@Component
@Slf4j
public class ChatHandler implements WebSocketHandler {
    private final Sinks.Many<String> sink = Sinks.many().multicast().directBestEffort();
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<String> output = session.receive()
                .map(message -> message.getPayloadAsText());

        output.doOnNext(s -> log.info(s));
        output.subscribe(s -> {
            log.info("emitData : {}, sessionId : {}", s, session.getId());
            sink.emitNext(s, Sinks.EmitFailureHandler.FAIL_FAST); // 데이터 발행
        });
        Flux<WebSocketMessage> massages = sink.asFlux().log("Sinks ??? ", Level.SEVERE).map(session1 -> {
            log.info("session1 : {}", session1);
            return session.textMessage(session1);
        });
        return session.send(massages);
    }
}
