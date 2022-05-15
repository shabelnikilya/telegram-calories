package org.example.parse;

import org.example.parse.valid.ValidationObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class PairKayValueParse implements Parse {
    private final static Logger LOG = LoggerFactory.getLogger(PairKayValueParse.class);
    private final static String SPLIT_LINE = ";";
    private final static String SPLIT_KEY_VALUE = "=";
    private final Map<String, Integer> params = new ConcurrentHashMap<>();
    private final List<ValidationObject> validations = new CopyOnWriteArrayList<>();

    @Override
    public void makeParse(String message) {
        LOG.info("Начат парсинг сообщения!");
        validateMessage(message);
        if (validations.isEmpty()) {
            String[] lines = message.split(SPLIT_LINE);
            Arrays.stream(lines)
                    .forEach(line -> {
                        validateLine(line);
                        if (validations.isEmpty()) {
                            String[] pair = line.split(SPLIT_KEY_VALUE);
                            params.put(pair[0], Integer.parseInt(pair[1]));
                        }
                    });
        }
    }

    @Override
    public List<ValidationObject> validationsResult() {
        return this.validations;
    }

    @Override
    public Map<String, Integer> getParams() {
        return this.params;
    }

    @Override
    public void clear() {
        validations.clear();
    }

    private void validateMessage(String message) {
        if (message.isEmpty()) {
            LOG.info("Принято пустое сообщение!");
            validations.add(new ValidationObject("Сообщение пустое! Невозможно произвети подсчет!"));
        }
    }

    private void validateLine(String line) {
        if (!line.contains(SPLIT_KEY_VALUE) || line.split(SPLIT_KEY_VALUE).length != 2
        || checkKeyValue(line)) {
            LOG.info("Принятое сообщение имеет не правильный формат: {}", line);
            validations.add(new ValidationObject("Не правильный формат ввода! Попробуйте снова!"));
        }
    }

    private boolean checkKeyValue(String line) {
        String[] pairs = line.split(SPLIT_KEY_VALUE);
        return Objects.isNull(pairs[0]) || pairs[0].isEmpty() || Objects.isNull(pairs[1])
                || pairs[1].isEmpty() || !isNumeric(pairs[1]);
    }

    private boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOG.info("Неверный формат значения! Значение - {}", value);
            return false;
        }
        return true;
    }
}
