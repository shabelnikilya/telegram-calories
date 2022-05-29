package org.example.bots;

import org.example.bots.reader.MessageReader;
import org.example.bots.reader.RegistryUser;
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

import java.util.Objects;

@Component
public class CaloriesBot extends TelegramLongPollingBot {
    private final static Logger LOG = LoggerFactory.getLogger(CaloriesBot.class);
    private final Service<Product> service;
    private final MemStore<SendAnswer> storeAnswer;
    private final MessageReader reader;

    @Autowired
    public CaloriesBot(Service<Product> service, MemStore<SendAnswer> storeAnswer, RegistryUser reader) {
        this.service = service;
        this.storeAnswer = storeAnswer;
        this.reader = reader;
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
        LOG.info("Сообщение принято!");
        Message message = update.getMessage();
        reader.readMessage(message);
        SendAnswer sendAnswer = storeAnswer.findByMessage(message.getText());
        sendMsg(message.getChatId().toString(), ifNullDefaultAnswer(sendAnswer).getAnswer());
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

    /**
     * Если не найдено операции, отправляется дефолтный ответ
     * @param sendAnswer проверяемый объект.
     * @return объект содержащий ответ.
     */
    private SendAnswer ifNullDefaultAnswer(SendAnswer sendAnswer) {
        return Objects.isNull(sendAnswer) ? storeAnswer.getDefaultMessage() : sendAnswer;
    }
}
