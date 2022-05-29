package org.example.models.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Answer {
    MENU(
            "В данном телеграм боте доступно следующее:" + System.lineSeparator()
            + "- показать краткую информацию о данном боте и его возможностях / команда 'info'"
            + System.lineSeparator()
            + "- показать формат ввода данных для внесения данных по калориям / команда 'format'"
            + System.lineSeparator()
            + "- Вывести все продукты данные по которым, есть в системе / 'all product'"
        ),
    INFO(
            "Данный телеграм бот предназначен для подсчета и ведении статистики потребления калорий!"
        ),
    FORMAT(
            "Данные следует вводить в такой формате -> Наименование продукта=количество грамм; ..."
            + System.lineSeparator() + "// Например -> Рыжики свежие=150;Белые свежие=200;"
        ),
    REGISTRY(
            "Вы успешно зарегестрированы!"
    );


    private final String text;
}
