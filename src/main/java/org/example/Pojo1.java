package org.example;

import org.jspecify.annotations.Nullable;

public class Pojo1 {

    private final @Nullable Pojo2 pojo2;

    public Pojo1(@Nullable Pojo2 pojo2) {
        this.pojo2 = pojo2;
    }

    public @Nullable Pojo2 getPojo2() {
        return pojo2;
    }
}
