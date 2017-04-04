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
                            .map(Pair::of)
                            .map(Pair.mapToTriple((number, word) -> numberToDayOfWeek.get(number)))
                            .map(Triple.mapToQuartet((number, word, oddOrEven) -> numberToRoman.get(number)))
                            .peek(System.out::println)
                            .collect(Collectors.toList());

        String joinedString = tuples.stream()
                         .flatMap(Quartet.flat(Object::toString,
                                               Function.identity(),
                                               DayOfWeek::name,
                                               Function.identity()))
                         .collect(Collectors.joining(", "));
        System.out.println(joinedString);
