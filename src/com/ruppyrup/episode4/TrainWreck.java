package com.ruppyrup.episode4;

public class TrainWreck {

    public static void main(String[] args) {
        X x = new X();
        x.getY().getZ().doSomething(); // don't do this
        x.doSomething(); // do this instead
    }
}

class X {
    private Y y = new Y();

    public Y getY() {
        return y;
    }

    public void doSomething() {
        y.doSomething();
    }
}


class Y {
    private Z z = new Z();

    public Z getZ() {
        return z;
    }

    public void doSomething() {
        z.doSomething();
    }
}

class Z {

    public void doSomething() {
        System.out.println("Something");
    }
}
