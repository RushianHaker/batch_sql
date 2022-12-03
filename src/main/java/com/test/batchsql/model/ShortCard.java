package com.test.batchsql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ShortCard - модель описывающая поля для работы с БД.
 */

@AllArgsConstructor
@Data
public class ShortCard {
    private int id;
    private String name;
    private String fullName;

    @Override
    public String toString() {
        return "ShortCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
