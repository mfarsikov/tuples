package com.tuples;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Pair<A, B> {

    private final A first;

    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public <C, D> Pair<C, D> map(Function<? super A, ? extends C> firstMap,
                                 Function<? super B, ? extends D> secondMap) {
        return new Pair<>(firstMap.apply(first), secondMap.apply(second));
    }

    public <C> Pair<C, B> mapFirst(Function<? super A, ? extends C> firstMap) {
        return map(firstMap, Function.identity());
    }

    public <C> Pair<A, C> mapSecond(Function<? super B, ? extends C> secondMap) {
        return map(Function.identity(), secondMap);
    }

    public <C> Triple<A, B, C> add(C third) {
        return new Triple<>(first, second, third);
    }

    public <C> Triple<A, B, C> compute(BiFunction<? super A, ? super B, ? extends C> f) {
        return add(f.apply(first, second));
    }

    public <C> Stream<C> stream(Function<? super A, ? extends C> firstMap,
                                Function<? super B, ? extends C> secondMap) {
        return Stream.of(firstMap.apply(first), secondMap.apply(second));
    }

    public static <A, B, C> Function<Pair<A, B>, Triple<A, B, C>> mapToTriple(
            BiFunction<? super A, ? super B, ? extends C> fun) {
        return pair -> pair.compute(fun);
    }

    static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    static <A, B> Pair<A, B> of(Map.Entry<A, B> entry) {
        return new Pair<>(entry.getKey(), entry.getValue());
    }

    public static <A, B, C> Function<Pair<A, B>, Stream<? extends C>> flat(Function<? super A, ? extends C> mapFirst,
                                                                           Function<? super B, ? extends C> mapSecond) {
        return pair -> pair.stream(mapFirst, mapSecond);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pair{");
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
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
               Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
