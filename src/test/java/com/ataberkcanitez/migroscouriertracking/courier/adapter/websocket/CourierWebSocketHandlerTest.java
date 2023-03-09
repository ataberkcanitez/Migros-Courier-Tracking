package com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket.dto.CourierLocationUpdateRequest;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourierWebSocketHandlerTest {

    private static final String MESSAGE_PAYLOAD = "{\"courierId\":1,\"location\":{\"lat\":50.0,\"lon\":25.0}}";

    @Mock
    private CourierLocationSubject courierObserver;

    @Mock
    private WebSocketSession session;

    private CourierWebSocketHandler courierWebSocketHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        courierWebSocketHandler = new CourierWebSocketHandler(courierObserver);
    }

    @Test
    public void testHandleTextMessage() throws Exception {
        Location location = new Location(50.0, 25.0);
        CourierLocationUpdateRequest request = new CourierLocationUpdateRequest(1L, location);
        TextMessage message = new TextMessage(MESSAGE_PAYLOAD);

        courierWebSocketHandler.handleTextMessage(session, message);
    }

    @Test
    public void testHandleTextMessageWithInvalidPayload() throws Exception {
        TextMessage message = new TextMessage("invalid payload");

        assertThrows(JsonParseException.class, () -> courierWebSocketHandler.handleTextMessage(session, message));
    }
}
