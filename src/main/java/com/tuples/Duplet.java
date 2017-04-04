package com.tuples;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Duplet<A, B> {

    private final A first;

    private final B second;

    private Duplet(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B, C> Function<Duplet<A, B>, Triplet<A, B, C>> mapToTriplet(
            BiFunction<? super A, ? super B, ? extends C> fun) {
        return duplet -> duplet.compute(fun);
    }

    public <C> Triplet<A, B, C> compute(BiFunction<? super A, ? super B, ? extends C> f) {
        return add(f.apply(first, second));
    }

    public <C> Triplet<A, B, C> add(C third) {
        return Triplet.of(first, second, third);
    }

    static <A, B> Duplet<A, B> of(A first, B second) {
        return new Duplet<>(first, second);
    }

    static <A, B> Duplet<A, B> of(Map.Entry<A, B> entry) {
        return new Duplet<>(entry.getKey(), entry.getValue());
    }

    public static <A, B, C> Function<Duplet<A, B>, Stream<? extends C>> flat(Function<? super A, ? extends C> mapFirst,
                                                                             Function<? super B, ? extends C> mapSecond) {
        return duplet -> duplet.stream(mapFirst, mapSecond);
    }

    public <C> Stream<C> stream(Function<? super A, ? extends C> firstMap,
                                Function<? super B, ? extends C> secondMap) {
        return Stream.of(firstMap.apply(first), secondMap.apply(second));
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public <C> Duplet<C, B> mapFirst(Function<? super A, ? extends C> firstMap) {
        return map(firstMap, Function.identity());
    }

    public <C, D> Duplet<C, D> map(Function<? super A, ? extends C> firstMap,
                                   Function<? super B, ? extends D> secondMap) {
        return new Duplet<>(firstMap.apply(first), secondMap.apply(second));
    }

    public <C> Duplet<A, C> mapSecond(Function<? super B, ? extends C> secondMap) {
        return map(Function.identity(), secondMap);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Duplet{");
        sb.append("first=").append(first);
        sb.append(", second=").append(second);
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
        Duplet<?, ?> duplet = (Duplet<?, ?>) o;
        return Objects.equals(first, duplet.first) &&
               Objects.equals(second, duplet.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
