package org.example.bots;

import org.example.bots.send.SendAnswer;
import org.example.models.Product;
import org.example.repository.MemStore;
import org.example.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CaloriesBot extends TelegramLongPollingBot {
    private final static Logger LOG = LoggerFactory.getLogger(CaloriesBot.class);
    private final Service<Product> service;
    private final MemStore<SendAnswer> storeAnswer;


    @Autowired
    public CaloriesBot(Service<Product> service, MemStore<SendAnswer> storeAnswer) {
        this.service = service;
        this.storeAnswer = storeAnswer;
        storeAnswer.init();
    }

    @Value("${bot.token}")
    private String token;

    @Value("${bot.name}")
    private String botName;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom());
        LOG.info("Сообщение принято!");
        Message message = update.getMessage();
        SendAnswer sendAnswer = storeAnswer.findByMessage(message.getText());
        sendMsg(message.getChatId().toString(), sendAnswer.getAnswer());
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            executeAsync(sendMessage);
            LOG.info("Сообщение отправлено!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
