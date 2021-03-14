package ru.motrichkin.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import ru.motrichkin.session.Bucket;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketController {

    private AtomicLong counter = new AtomicLong(0);

    @Autowired
    private Bucket bucket;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OutputMessage getMessage(Message message) throws InterruptedException {
        Thread.sleep(300);
        counter.addAndGet(1);
        return new OutputMessage("Сообщение через вебсокет " + counter.toString());
    }
}
