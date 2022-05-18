package org.example.bots.send;

public class SimpleTextAnswer implements SendAnswer {
    private final String info;

    public SimpleTextAnswer(String info) {
        this.info = info;
    }

    @Override
    public String getAnswer() {
        return info;
    }
}
