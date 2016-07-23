package com.tieto.webapp;

/**
 * Created by Daniel Zvir on 20.07.2016.
 */
public class Property {
    private final String key;
    private final String value;

    Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
