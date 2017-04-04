# Java 8 Tuples
## Type-safe
### Build using call chain

        Triplet<Integer, String, DayOfWeek> tuple1 = Unit.of(1)
                                                         .add("Some string")
                                                         .add(DayOfWeek.MONDAY);

### Factory methods
         Quartet<Integer, String, Object, DayOfWeek> tuple2 = Tuple.of(33,
                                                                       "String",
                                                                       new Object(),
                                                                       DayOfWeek.SUNDAY);
### Java 8 Stream friendly
#### Map
                map.entrySet().stream()
                   .map(Duplet::of)                                                                 // Direct mapping from Map.Entry
                   .map(Duplet.mapToTriplet((number, word) -> numberToDayOfWeek.get(number)))       // Duplet -> Triplet
                   .map(Triplet.mapToQuartet((number, word, dayOfWeek) -> numberToRoman.get(word))) // Triplet -> Quartet
#### Flatmap

        List<Quartet<Integer, String, DayOfWeek, String>> tuples = ...
        Stream<String> = tuples.stream()
                               .flatMap(Quartet.flat(Object::toString,
                                                     Function.identity(),
                                                     DayOfWeek::name,
                                                     Function.identity()))
#### Working example
See Example class in test directory
