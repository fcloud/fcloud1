package com.fcloud.demo.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Ruben Fu
 */
@DatabaseTable(tableName = "demo1")
@TableOrder(10000)
public class MyDemo extends Entity {

    @DatabaseField(columnName = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
