# Java 8 Tuples
Just tuples, we were waiting for.

#### What is Tuple? 
Tuple like fixed length array (usually small enough) of different types objects. 
But unlike an array tuple elements are type-safe.
Most famous example is a `com.example.util.Pair<K, V>` it is a tuple of two elements.

#### How do I use Tuples?
Tuples are quite useful in light of Java Streams: 
often we have to gather some data before we create resulting object. 
So in this case we could use tuple of two elements, then map them to tuple of three elements, then to four...

## Features

### Build using call chain

    Triplet<Integer, String, DayOfWeek> tuple1 = Unit.of(1)
                                                     .add("Some string")
                                                     .add(DayOfWeek.MONDAY);

### Factory methods
    Quartet<Integer, String, Object, DayOfWeek> tuple2 = Tuple.of(33,
                                                                  "String",
                                                                  new Object(),
                                                                  DayOfWeek.SUNDAY);
### Map
		example
## Java 8 Stream friendly
#### Map
    map.entrySet().stream()
       .map(Duplet::of)                                                                 // Direct mapping from Map.Entry
       .map(Duplet.mapToTriplet((number, word) -> numberToDayOfWeek.get(number)))       // Duplet -> Triplet
       .map(Triplet.mapToQuartet((number, word, dayOfWeek) -> numberToRoman.get(word))) // Triplet -> Quartet
       
#### Convertable to Stream
		example

#### Flatmap

    List<Quartet<Integer, String, DayOfWeek, String>> tuples = ...
    Stream<String> = tuples.stream()
                           .flatMap(Quartet.flat(Object::toString,
                                                 Function.identity(),
                                                 DayOfWeek::name,
                                                 Function.identity()))
                                                 
### Working example
See Example class in test directory
