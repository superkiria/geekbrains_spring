package ru.motrichkin.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketController {

    private static final AtomicLong counter = new AtomicLong(0);

    @Autowired
    SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/hello")
    public void getMessage(Message message) throws InterruptedException {
        Thread.sleep(300);
        counter.addAndGet(1);
        messagingTemplate.convertAndSend("/queue/greetings", new OutputMessage("Сообщение через вебсокет " + counter.toString()));
    }

    public void sendMessage(String destination, OutputMessage outputMessage) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(700);
                messagingTemplate.convertAndSend("/queue/greetings", outputMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        (new Thread(runnable)).start();
    }
}
