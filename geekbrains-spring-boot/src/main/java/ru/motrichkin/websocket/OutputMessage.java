package ru.motrichkin.websocket;

public class OutputMessage {
    private String content;

    public OutputMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
