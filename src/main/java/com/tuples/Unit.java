package com.tuples;

import java.util.Objects;
import java.util.function.Function;

public class Unit<A> {
    private final A first;

    private Unit(A first) {
        this.first = first;
    }

    public static <A, B> Function<Unit<A>, Pair<A, B>> mapToPair(Function<A, B> mapFirst) {
        return unit -> Pair.of(unit.first, mapFirst.apply(unit.first));
    }

    public static <A> Unit<A> of(A first) {
        return new Unit<>(first);
    }

    public <B> Pair<A, B> add(B second) {
        return Pair.of(first, second);
    }

    public <B> Unit<B> map(Function<A, B> mapFirst) {
        return new Unit<B>(mapFirst.apply(first));
    }

    public A getFirst() {
        return first;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Unit{");
        sb.append("first=").append(first);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Unit<?> unit = (Unit<?>) o;
        return Objects.equals(first, unit.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
