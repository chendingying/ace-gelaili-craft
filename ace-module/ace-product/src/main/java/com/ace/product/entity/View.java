package com.ace.product.entity;

import javax.persistence.Table;

/**
 * Created by CDZ on 2018/11/22.
 */
@Table(name = "view_bom_item")
public class View {
    private Integer id;
    private String code;
    private String name;
    private String value2_30;
    private String value3_52;
    private String value3_53;
    private String value_else;

    public String getValue_else() {
        return value_else;
    }

    public void setValue_else(String value_else) {
        this.value_else = value_else;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue2_30() {
        return value2_30;
    }

    public void setValue2_30(String value2_30) {
        this.value2_30 = value2_30;
    }

    public String getValue3_52() {
        return value3_52;
    }

    public void setValue3_52(String value3_52) {
        this.value3_52 = value3_52;
    }

    public String getValue3_53() {
        return value3_53;
    }

    public void setValue3_53(String value3_53) {
        this.value3_53 = value3_53;
    }
}
