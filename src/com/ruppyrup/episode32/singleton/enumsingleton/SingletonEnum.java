package com.ruppyrup.episode32.singleton.enumsingleton;


import static org.junit.jupiter.api.Assertions.assertEquals;

public enum SingletonEnum {
    INSTANCE(MyStatic.K);

    public static int K = 1;

    int value;

    SingletonEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class MyStatic {
    public static MyStatic instance = new MyStatic(SingletonEnum.K);
    public static int K=2;
    public int constant;

    private MyStatic(int n) {
        constant = n;
    }
}

class EnumDemo {
    public static void main(String[] args) {
        assertEquals(1, MyStatic.instance.constant);
        assertEquals(2, SingletonEnum.INSTANCE.getValue());
    }
}
