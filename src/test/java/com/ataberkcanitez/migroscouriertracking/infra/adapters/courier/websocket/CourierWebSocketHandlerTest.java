package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.dto.CourierLocationUpdateRequest;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@RunWith(MockitoJUnitRunner.class)
public class CourierWebSocketHandlerTest {

    private static final String MESSAGE_PAYLOAD = "{\"courierId\":1,\"location\":{\"lat\":50.0,\"lon\":25.0}}";

    @Mock
    private CourierLocationSubject courierObserver;

    @Mock
    private WebSocketSession session;

    @InjectMocks
    private CourierWebSocketHandler courierWebSocketHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleTextMessage() throws Exception {
        Location location = new Location(50.0, 25.0);
        CourierLocationUpdateRequest request = new CourierLocationUpdateRequest(1L, location);
        TextMessage message = new TextMessage(MESSAGE_PAYLOAD);

        courierWebSocketHandler.handleTextMessage(session, message);
    }

    @Test(expected = JsonParseException.class)
    public void testHandleTextMessageWithInvalidPayload() throws Exception {
        TextMessage message = new TextMessage("invalid payload");

        courierWebSocketHandler.handleTextMessage(session, message);
    }
}
