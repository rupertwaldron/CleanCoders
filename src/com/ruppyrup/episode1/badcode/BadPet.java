package com.ruppyrup.episode1.badcode;

import java.time.LocalDate;
import java.util.Objects;

class BadPet {
    private String name;
    private String type;
    private LocalDate exerciseDate;

    public BadPet(final String name, final String type) {
        this.name = name;
        this.type = type;
        exerciseDate = LocalDate.now().minusDays(2);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public LocalDate getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(final LocalDate exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final BadPet badPet = (BadPet) o;

        return Objects.equals(name, badPet.name);
    }
}
