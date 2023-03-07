package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.dto.CourierLocationUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@NoArgsConstructor
public class CourierLocationHandler extends TextWebSocketHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        CourierLocationUpdateRequest request = objectMapper.readValue(message.getPayload(), CourierLocationUpdateRequest.class);
        System.out.println(request.toString());
    }
}