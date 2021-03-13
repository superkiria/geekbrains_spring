package ru.motrichkin.rest;

import org.springframework.stereotype.Controller;
import ru.motrichkin.websocket.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Controller
public class WebSocketController {
    @MessageMapping("/productAddedToBucket")
    @SendTo("/topic/productAddedToBucketSuccess")
    public Message getMessage() throws InterruptedException {
        Thread.sleep(3000);
        return new Message("Товар добавлен в корзину");
    }
}
