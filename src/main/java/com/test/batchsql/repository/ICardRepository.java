package com.test.batchsql.repository;

import com.test.batchsql.model.ShortCard;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * Интерфейс ICardRepository описывает методы работы с БД
 */
public interface ICardRepository {

    /**
     * Отдает список карточек модели из БД
     */
    List<ShortCard> batchSelect(@NotNull List<String> names);


    /**
     * Сохраняет список карточек модели из БД
     */
    void batchInsert(@NotNull List<ShortCard> shortCards);

}
