package org.example.bots.reader;

import org.example.models.UserDB;
import org.example.parse.PairKeyValueParse;
import org.example.parse.Parse;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class RegistryUser implements MessageReader {
    private final static Logger LOG = LoggerFactory.getLogger(RegistryUser.class);
    private final static String OPERATION = "registry";
    private final String ageKey = "age";
    private final String weightKey = "weight";
    private final UserService service;
    private final Parse parse;

    @Autowired
    public RegistryUser(UserServiceImpl service, PairKeyValueParse parse) {
        this.service = service;
        this.parse = parse;
    }

    @Override
    public void readMessage(Message message) {
        LOG.info("В работе метод регистрации пользователя!");

        String textMessage = message.getText();
        String head = getHead(textMessage);

        LOG.info("Заголовок сообщения: {}", head);
        if (OPERATION.equalsIgnoreCase(head)) {
            parse.makeParse(textMessage.split("\n")[1]);

            LOG.info("Строка, которая парсится - {}", textMessage.split("\n")[1]);
            LOG.info("Количество лет и вес: {}, {}", parse.getValue(ageKey), parse.getValue(weightKey));
            User user = message.getFrom();
            UserDB userRegistry = UserDB.builder()
                    .userName(user.getUserName())
                    .firstName(user.getFirstName())
                    .secondName(user.getLastName())
                    .age(Integer.parseInt(parse.getValue(ageKey)))
                    .weight(Float.parseFloat(parse.getValue(weightKey)))
                    .isBot(user.getIsBot())
                    .enable(true)
                    .build();

            LOG.info("Сохраненный пользователь - {}", userRegistry);

            service.save(userRegistry);

            LOG.info("Регистрация пользователя прошла успешно!");
        }
    }

    private String getHead(String message) {
        return message.contains("\n") ?
                message.split("\n")[0] : null;
    }
}
