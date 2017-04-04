package com.tuples;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example {
    public static void main(String[] args) {
        Map<Integer, String> numberToWord = new HashMap<>();
        numberToWord.put(1, "one");
        numberToWord.put(2, "two");
        numberToWord.put(3, "three");

        Map<Integer, DayOfWeek> numberToDayOfWeek = new HashMap<>();
        numberToDayOfWeek.put(1, DayOfWeek.MONDAY);
        numberToDayOfWeek.put(2, DayOfWeek.TUESDAY);
        numberToDayOfWeek.put(3, DayOfWeek.WEDNESDAY);

        Map<Integer, String> numberToRoman = new HashMap<>();
        numberToRoman.put(1, "I");
        numberToRoman.put(2, "II");
        numberToRoman.put(3, "III");

        List<Quartet<Integer, String, DayOfWeek, String>> tuples =
                numberToWord.entrySet().stream()
                            .map(Duplet::of)
                            .map(Duplet.mapToTriplet((number, word) -> numberToDayOfWeek.get(number)))
                            .map(Triplet.mapToQuartet((number, word, dayOfWeek) -> numberToRoman.get(number)))
                            .peek(System.out::println)
                            .collect(Collectors.toList());

        /*  Output:
            Quartet{first=1, second=one, third=MONDAY, fourth=I}
            Quartet{first=2, second=two, third=TUESDAY, fourth=II}
            Quartet{first=3, second=three, third=WEDNESDAY, fourth=III}
         */

        String joinedString = tuples.stream()
                                    .flatMap(Quartet.flat(Object::toString,
                                                          Function.identity(),
                                                          DayOfWeek::name,
                                                          Function.identity()))
                                    .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(joinedString);
        /*  Output:
            {1, one, MONDAY, I, 2, two, TUESDAY, II, 3, three, WEDNESDAY, III}
         */
    }
}
