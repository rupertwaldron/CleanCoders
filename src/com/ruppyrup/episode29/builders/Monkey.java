package com.ruppyrup.episode29.builders;

public class Monkey {
    private String name;
    private int age;
    private Food food;

    // private constructor
    private Monkey() {}

    public static MonkeyBuilder builder() {
        return new MonkeyBuilder();
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", food=" + food +
                '}';
    }

    public static class MonkeyBuilder {
        private String name;
        private int age;
        private Food food;

        public MonkeyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MonkeyBuilder age(int age) {
            this.age = age;
            return this;
        }

        public MonkeyBuilder food(Food food) {
            this.food = food;
            return this;
        }

        public Monkey build() {
            Monkey monkey = new Monkey();
            monkey.name = this.name;
            monkey.age = this.age;
            monkey.food = this.food;
            return monkey;
        }
    }

    public enum Food {
        BANANNA,
        APPLE
    }

    public static void main(String[] args) {
        Monkey monkey = Monkey.builder()
                .age(2)
                .name("Coco")
                .food(Food.APPLE)
                .build();
        System.out.println("Monkey -> " + monkey);
    }
}
