package com.tuples;

public class Tuple {

    private Tuple() {
    }

    public static <A> Unit<A> of(A firtst) {
        return Unit.of(firtst);
    }

    public static <A, B> Duplet<A, B> of(A first, B second) {
        return Duplet.of(first, second);
    }

    public static <A, B, C> Triplet<A, B, C> of(A first, B second, C third) {
        return Triplet.of(first, second, third);
    }

    public static <A, B, C, D> Quartet<A, B, C, D> of(A first, B second, C third, D fourth) {
        return Quartet.of(first, second, third, fourth);
    }
}
