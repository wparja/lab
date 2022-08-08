package com.wparja.myfloatingbutton.Component.Animation;

public enum OrderEnum {

    DEFAULT(0),
    REVERSE(1),
    RANDOM (2),

    Unknown(-1);

    private final int value;

    OrderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }
}
