package com.tuples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PairTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        map.entrySet().stream()
           .map(Pair::of)
           .map(Pair.mapToTriple((a, b) -> a + b))
           .map(Triple.mapToQurtet((a, b, c) -> a + b + c))
           .forEach(System.out::println);

        map.entrySet().stream()
           .map(Pair::of)
           .flatMap(Pair.flat(Object::toString, Function.identity()))
           .forEach(System.out::println);
    }
}
