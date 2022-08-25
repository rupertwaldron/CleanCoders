package com.ruppyrup.episode1.goodcode;

public abstract class Pet {
    private final String name;

    protected Pet(final String name) {
        this.name = name;
    }

    String getPetName() {
        return name;
    }

    abstract boolean isPetType(String petType);

}

interface Exerciseable {
    void exercise();
}

class Dog extends Pet implements Exerciseable {

    public Dog(final String name) {
        super(name);
    }

    @Override
    public boolean isPetType(String petType) {
        return "Dog".equals(petType);
    }

    @Override
    public void exercise() {
        System.out.println("Take dog for a walk");
    }
}

class Snake extends Pet {

    public Snake(final String name) {
        super(name);
    }

    @Override
    public boolean isPetType(String petType) {
        return "Snake".equals(petType);
    }
}
