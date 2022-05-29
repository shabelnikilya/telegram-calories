package org.example.repository;

import org.example.bots.send.SimpleTextAnswer;
import org.example.bots.send.ListNameProductAnswer;
import org.example.bots.send.SendAnswer;
import org.example.models.Product;
import org.example.models.message.Answer;
import org.example.service.ProductService;
import org.example.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemAnswerStore implements MemStore<SendAnswer> {
    private final static Logger LOG = LoggerFactory.getLogger(MemAnswerStore.class);
    private final Map<String, SendAnswer> store = new ConcurrentHashMap<>();
    private final Service<Product> service;

    @Autowired
    public MemAnswerStore(ProductService service) {
        this.service = service;
    }

    @Override
    public void init() {
        LOG.info("Начата инициализация хранилища в ОП!");
        store.put("menu", new SimpleTextAnswer(Answer.MENU.getText()));
        store.put("info", new SimpleTextAnswer(Answer.INFO.getText()));
        store.put("format", new SimpleTextAnswer(Answer.FORMAT.getText()));
        store.put("all product", new ListNameProductAnswer(service.findAll()));
    }

    @Override
    public SendAnswer findByMessage(String message) {
        return store.get(message);
    }

    @Override
    public SendAnswer getDefaultMessage() {
        StringBuilder str = new StringBuilder();
        str.append("Такой операции не существует или не корректна напечатана!")
                .append(System.lineSeparator())
                .append("Проверьте наличие/правильность операции")
                .append(System.lineSeparator())
                .append(store.get("menu").getAnswer());
        return new SimpleTextAnswer(str.toString());
    }

    @Override
    public void save(String message, SendAnswer sendAnswer) {
        store.put(message, sendAnswer);
    }

    @Override
    public void update(String message, SendAnswer sendAnswer) {
        store.computeIfPresent(message, (key, v) -> sendAnswer);
    }

    @Override
    public void delete(String message) {
        store.remove(message);
    }
}
