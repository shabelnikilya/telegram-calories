package org.example.bots;

import org.example.utils.PropertiesReader;
import org.example.utils.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Элементарная реализация бота.
 */
@Deprecated
public class Bot extends TelegramLongPollingBot {

    private final static Logger LOG = LoggerFactory.getLogger(TelegramLongPollingBot.class);
    private final Reader reader;

    public Bot() {
        LOG.info("Произошла инициализация бота!");

        reader = new PropertiesReader();
        reader.init("src/main/resources/app.properties");
    }

    @Override
    public String getBotUsername() {
        return reader.getProperties("telegram.name");
    }

    @Override
    public String getBotToken() {
        return reader.getProperties("telegram.token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();

        LOG.info("Сообщение на сервер доставлено: " + message);

        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    private synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка в отправке сообщения", e);
        }
    }
}
