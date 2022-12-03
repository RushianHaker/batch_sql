package com.test.batchsql.repository.implemented;


import com.test.batchsql.model.ShortCard;
import com.test.batchsql.repository.ICardRepository;
import com.test.batchsql.repository.mapper.ShortCardMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Класс CardRepository - реализует методы работы обработки информации по карточкам
 */

@Slf4j
@Repository
public class CardRepository implements ICardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final ShortCardMapper SHORT_CARD_MAPPER = new ShortCardMapper();

    public CardRepository(@Qualifier("postgres") NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          @Qualifier("postgres") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<ShortCard> batchSelect(@NotNull List<String> names) {

        /*С пустым IN запрос не пройдёт в этом виде, но оно и не нужно*/
        if (names.size() == 0) return new ArrayList<>();

        SqlParameterSource param = new MapSqlParameterSource("names", names);

        return namedParameterJdbcTemplate.query(
                "select id, \"name\", full_name from cards where \"name\" in ( :names ) group by id",
                param, SHORT_CARD_MAPPER);
    }

    @Override
    public void batchInsert(@NotNull List<ShortCard> shortCards) {
        try {
            List<Object[]> batchArgs = new ArrayList<>();
            for (var card : shortCards) {
                batchArgs.add(new Object[]{
                        card.getId(), card.getName(), card.getFullName()
                });
            }

            jdbcTemplate.batchUpdate("insert into cards (id, \"name\", full_name) values (?, ?, ?)", batchArgs);
            log.trace("batchInsert(): post request with [{}] shortCards is added", shortCards.size());
        } catch (DataAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}