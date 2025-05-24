package org.example;

import org.jspecify.annotations.Nullable;

public class Pojo2 {

    private final @Nullable Pojo3 pojo3;

    public Pojo2(@Nullable Pojo3 pojo3) {
        this.pojo3 = pojo3;
    }

    public @Nullable Pojo3 getPojo3() {
        return pojo3;
    }
}
