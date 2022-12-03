package com.test.batchsql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * В этой модели ShortCard - описание полей для работы с БД.
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
