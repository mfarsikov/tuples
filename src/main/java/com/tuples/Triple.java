package com.tuples;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import com.tuples.misc.TriFunction;

public class Triple<A, B, C> {

    private final A first;

    private final B second;

    private final C third;

    private Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <A, B, C, D> Function<Triple<A, B, C>, Stream<? extends D>> flat(
            Function<? super A, ? extends D> mapFirst,
            Function<? super B, ? extends D> mapSecond,
            Function<? super C, ? extends D> mapThird) {

        return triple -> triple.stream(mapFirst, mapSecond, mapThird);
    }

    public <D> Stream<D> stream(Function<? super A, ? extends D> mapFirst,
                                Function<? super B, ? extends D> mapSecond,
                                Function<? super C, ? extends D> mapThird) {

        return Stream.of(mapFirst.apply(first), mapSecond.apply(second), mapThird.apply(third));
    }

    public static <A, B, C, D> Function<Triple<A, B, C>, Quartet<A, B, C, D>> mapToQuartet(
            TriFunction<? super A, ? super B, ? super C, ? extends D> fun) {

        return triple -> triple.add(fun.apply(triple.first, triple.second, triple.third));
    }

    public <D> Quartet<A, B, C, D> add(D fourth) {

        return Quartet.of(first, second, third, fourth);
    }

    public static <A, B, C> Triple<A, B, C> of(A first, B second, C third) {

        return new Triple<>(first, second, third);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Triple{");
        sb.append("first=").append(first);
        sb.append(", second=").append(second);
        sb.append(", third=").append(third);
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
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(first, triple.first) &&
               Objects.equals(second, triple.second) &&
               Objects.equals(third, triple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
