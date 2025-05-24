package org.example;

import org.jspecify.annotations.Nullable;

public class Pojo0 {

    private final @Nullable Pojo1 pojo1;

    public Pojo0(@Nullable Pojo1 pojo1) {
        this.pojo1 = pojo1;
    }

    public @Nullable Pojo1 getPojo1() {
        return pojo1;
    }
}
