package com.test.batchsql.repository.mapper;

import com.test.batchsql.model.ShortCard;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ShortCardMapper implements RowMapper<ShortCard> {
    @NotNull
    @Override
    public ShortCard mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new ShortCard(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("full_name"));
    }
}