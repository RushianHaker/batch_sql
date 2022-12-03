package com.test.batchsql.service;

import com.test.batchsql.model.ShortCard;
import com.test.batchsql.repository.ICardRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс CardService - общий метод для работы с контроллерами
 */

@AllArgsConstructor
@Service
public class CardService {
    protected final ICardRepository cardRepository;

    /**
     * Отдает список карточек модели из БД
     */
    @NotNull
    public List<ShortCard> allCardsListByNames(@NotNull List<String> names) {
        if(names.isEmpty()) return new ArrayList<>();
        return cardRepository.batchSelect(names);
    }

    /**
     * Сохраняет список карточек модели в БД
     */
    public void saveCardsList(@NotNull List<ShortCard> shortCards) {
        if(shortCards.isEmpty()) return;

        cardRepository.batchInsert(shortCards);
    }
}