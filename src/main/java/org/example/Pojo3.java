package org.example;

import org.jspecify.annotations.Nullable;

public class Pojo3 {

    private @Nullable String name;

    public Pojo3(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }
}
