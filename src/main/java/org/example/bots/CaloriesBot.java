package org.example.bots;

import org.example.models.Product;
import org.example.parse.PairKayValueParse;
import org.example.parse.Parse;
import org.example.service.Service;
import org.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CaloriesBot extends TelegramLongPollingBot {
    private final static Logger LOG = LoggerFactory.getLogger(CaloriesBot.class);
    private final Service<Product> service;
    private final Parse parse;

    @Autowired
    public CaloriesBot(ProductService service, PairKayValueParse parse) {
        this.service = service;
        this.parse = parse;
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
        StringBuilder str = new StringBuilder();
        String message = update.getMessage().getText();
        parse.makeParse(message);
        if (!parse.validationsResult().isEmpty()) {
            LOG.info("Отправлен ответ список ошибок!");
            parse.validationsResult().forEach(valid -> str.append(valid.getText()).append(System.lineSeparator()));
            parse.clear();
        } else {
            LOG.info("Отправлен корректный ответ!");
            str.append("Парсинг прошел успешно!");
        }
        sendMsg(update.getMessage().getChatId().toString(), str.toString());
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            executeAsync(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
