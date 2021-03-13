package ru.motrichkin.websocket;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.motrichkin.websocket.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Controller
public class WebSocketController {
    @MessageMapping("/productAddedToBucket")
    @SendTo("/topic/productAddedToBucketSuccess")
    public OutputMessage getMessage(Message message) throws InterruptedException {
        Thread.sleep(3000);
        return new OutputMessage("Товар добавлен в корзину");
    }
}
