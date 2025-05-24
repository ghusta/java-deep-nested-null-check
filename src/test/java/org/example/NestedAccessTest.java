package org.example;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NestedAccessTest {

    private Pojo0 pojoWithValue;
    private Pojo0 pojoWithoutValue;

    @BeforeEach
    void setUp() {
        pojoWithValue = new Pojo0(new Pojo1(new Pojo2(new Pojo3("Hello"))));
        pojoWithoutValue = new Pojo0(new Pojo1(new Pojo2(null)));
    }

    @Nested
    class WithNonNullValues {

        @Test
        void with_exception_control_flow() {
            String value = extractNameWithExceptionControlFlow(pojoWithValue);
            assertThat(value).isNotNull();
        }

        @Test
        void with_exception_control_flow_ternary() {
            String value = extractNameWithNormalControlFlowTernaryOperator(pojoWithValue);
            assertThat(value).isNotNull();
        }

        @Test
        void with_optional() {
            String value = extractNameWithOptional(pojoWithValue);
            assertThat(value).isNotNull();
        }

        @Test
        void with_pattern_matching_instanceof() {
            String value = extractNameWithPatternMatchingInstanceOf(pojoWithValue);
            assertThat(value).isNotNull();
        }

    }

    @Nested
    class WithNullableValues {

        @Test
        void with_exception_control_flow() {
            String value = extractNameWithExceptionControlFlow(pojoWithoutValue);
            assertThat(value).isNull();
        }

        @Test
        void with_exception_control_flow_ternary() {
            String value = extractNameWithNormalControlFlowTernaryOperator(pojoWithoutValue);
            assertThat(value).isNull();
        }

        @Test
        void with_optional() {
            String value = extractNameWithOptional(pojoWithoutValue);
            assertThat(value).isNull();
        }

        @Test
        void with_pattern_matching_instanceof() {
            String value = extractNameWithPatternMatchingInstanceOf(pojoWithoutValue);
            assertThat(value).isNull();
        }

    }

    static @Nullable String extractNameWithExceptionControlFlow(@Nullable Pojo0 pojo0) {
        try {
            return pojo0.getPojo1().getPojo2().getPojo3().getName();
        } catch (NullPointerException e) {
            return null;
        }
    }

    static @Nullable String extractNameWithNormalControlFlow(@Nullable Pojo0 pojo0) {
        if (pojo0 != null &&
                pojo0.getPojo1() != null &&
                pojo0.getPojo1().getPojo2() != null &&
                pojo0.getPojo1().getPojo2().getPojo3() != null) {
            return pojo0.getPojo1().getPojo2().getPojo3().getName();
        }
        return null;
    }

    static @Nullable String extractNameWithNormalControlFlowTernaryOperator(@Nullable Pojo0 pojo0) {
        Pojo1 pojo1 = pojo0 != null ? pojo0.getPojo1() : null;
        Pojo2 pojo2 = pojo1 != null ? pojo1.getPojo2() : null;
        Pojo3 pojo3 = pojo2 != null ? pojo2.getPojo3() : null;
        return pojo3 != null ? pojo3.getName() : null;
    }

    static @Nullable String extractNameWithOptional(@Nullable Pojo0 pojo0) {
        return Optional.ofNullable(pojo0)
                .map(Pojo0::getPojo1)
                .map(Pojo1::getPojo2)
                .map(Pojo2::getPojo3)
                .map(Pojo3::getName)
                .orElse(null);
    }

    static String extractNameWithPatternMatchingNestedRecords(Pojo0 pojo0) {
        // must be records + Java 21
        // see : https://openjdk.org/jeps/405
//        if (pojo0 instanceof Pojo0(Pojo1 pojo1)
//                && pojo1 instanceof Pojo1(Pojo2 pojo2)
//                && pojo2 instanceof Pojo2(Pojo3 pojo3)
//                && pojo3 instanceof Pojo3(String name)) {
//            return name;
//        }
//        return null;
        throw new UnsupportedOperationException("Not records");
    }

    static @Nullable String extractNameWithPatternMatchingInstanceOf(@Nullable Pojo0 pojo0) {
        // see : https://openjdk.org/jeps/394
        if (pojo0 != null) {
            Pojo1 p1 = pojo0.getPojo1();
            if (p1 instanceof Pojo1) {
                Pojo2 p2 = p1.getPojo2();
                if (p2 instanceof Pojo2) {
                    Pojo3 p3 = p2.getPojo3();
                    if (p3 instanceof Pojo3) {
                        return p3.getName();
                    }
                }
            }
        }
        return null;
    }

}
