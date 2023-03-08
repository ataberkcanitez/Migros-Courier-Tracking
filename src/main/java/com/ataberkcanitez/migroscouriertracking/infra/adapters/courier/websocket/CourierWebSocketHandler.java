package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.dto.CourierLocationUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CourierWebSocketHandler extends TextWebSocketHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final CourierLocationSubject courierObserver;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        CourierLocationUpdateRequest request = objectMapper.readValue(message.getPayload(), CourierLocationUpdateRequest.class);
        courierObserver.notifyObservers(request.getCourierId(), request.getLocation());
    }
}