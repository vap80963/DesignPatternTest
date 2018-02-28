package com.example.admin.myapplication.lambda_stream;

/**
 * Created by hasee on 12/4/2017.
 *
 * @author tin
 */

public class Transaction {

    public static final String GROCERY = "grocery";

    int id;
    String value;
    String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
