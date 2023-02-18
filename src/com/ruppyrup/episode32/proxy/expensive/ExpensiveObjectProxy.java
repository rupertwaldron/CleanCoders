package com.ruppyrup.episode32.proxy.expensive;

import java.util.Optional;

public class ExpensiveObjectProxy implements ExpensiveObject {
    private static ExpensiveObject object;

    @Override
    public void process() {

        object = Optional.ofNullable(object).orElseGet(ExpensiveObjectImpl::new);

//        if (object == null) {
//            object = new ExpensiveObjectImpl();
//        }

        System.out.println("Before object process");
        object.process();
        System.out.println("After object process");
    }
}
