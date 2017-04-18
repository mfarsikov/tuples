# Java 8 Tuples
Just tuples, we were waiting for.

#### What is Tuple? 

Tuple like fixed length (usually small enough) array of heterogeneous objects (each element has different type).
In Java code it could be explained as:

     Object[] array = {1, "2", WEDNESSDAY};
     Integer i = (Integer) array[0];
     String s = (String) array[1];
     DayOfWeek d = (DayOfWeek) array[2];
      
But unlike an array tuples are type-safe.
Most famous example is a `com.example.util.Pair<K, V>` it is a tuple of two elements.

#### How do I use Tuples?
Tuples are quite useful in light of Java Streams: 
often we have to gather some data before we create resulting object. 
So in this case we could use tuple of two elements, then map them to tuple of three elements, then to four...

## Features

### Build using call chain

    Quartet<Integer, String, DayOfWeek, Object> quartet = Unit.of(1)
                                                              .add("Some string")
                                                              .add(DayOfWeek.MONDAY)
                                                              .add(new Object());

### Factory methods
    Duplet<Integer, Stringk> duplet = Tuple.of(33, "String");
### Map

    Function<Integer, Integer> twice = i -> i * 2;
    Function<Integer, Double> quarter = i -> i / 4.0;
    Function<String, String> addMartin = s -> s + "Martin";

    Duplet<Double, String> tuple = Tuple.of(1, "hello ")
                                        .map(twice, addMartin)
                                        .mapFirst(quarter)
                                        .mapSecond(String::toUpperCase);
    // tuple contain {0.5, "HELLO MARTIN"}
## Java 8 Stream friendly

#### Direct mapping from `java.util.Map.Entry`
    Map<Integer, String> integerToString = new HashMap<>();
    
    Stream<Duplet<Integer, String>> dupletStream = integerToString.entrySet()
                                                                  .stream()
                                                                  .map(Duplet::of);

#### Map operation
Sometimes it needs to "accumulate" values through stream processing, 
and add one more element on each map operation.
For example imagine transformations: 

    {2} -> {2, "two"} -> {2, "two", TUESDAY} -> {2, "two", TUESDAY, "II"}

It could be done as following:

    numbers.stream()
           .map(Tuple::of)                                                                  // Unit<Integer>
           .map(Unit.mapToDuplet(numberToWord::get))                                        // Duplet<Integer, String>
           .map(Duplet.mapToTriplet((number, word) -> numberToDayOfWeek.get(number)))       // Triplet<Integer, String, DayOfWeek>
           .map(Triplet.mapToQuartet((number, word, dayOfWeek) -> numberToRoman.get(word))) // Quartet<Integer, String, DayOfWeek, String>
       
#### Convertable to Stream
Tuples could be converted to stream in case if there will be provided functions
for converting each Tuple element to some homogeneous type:

    Stream<String> strings = Tuple.of(2, "two")
                                  .stream(Object::toString, 
                                          Function.identity());
 

#### Flatmap

    List<Quartet<Integer, String, DayOfWeek, String>> tuples = ...
    Stream<String> = tuples.stream()
                           .flatMap(Quartet.flat(Object::toString,
                                                 Function.identity(),
                                                 DayOfWeek::name,
                                                 Function.identity()))

Further these strings could be joined using:

    .collect(Collectors.joining(", ", "{", "}"));
                                                 
### Working example
See Example class in test directory
