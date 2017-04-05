package org.comprehension.tuple;

import java.util.stream.Stream;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DupletTest {
    @Test
    public void map() throws Exception {
        Duplet<String, String> tuple = Tuple.of(1, 1)
                                            .map(Object::toString, Object::toString);

        assertThat(tuple, is(Tuple.of("1", "1")));
    }

    @Test
    public void mapToTriplet() throws Exception {
        Triplet<Integer, Integer, Integer> tuple =
                Stream.of(Duplet.of(1, 1))
                      .map(Duplet.mapToTriplet((Number i, Number j) -> i.intValue() + j.intValue()))
                      .findAny()
                      .get();

        assertThat(tuple, is(Tuple.of(1, 1, 2)));
    }

    @Test
    public void compute() throws Exception {
        Triplet<Integer, Integer, Integer> tuple = Duplet.of(1, 2)
                                                         .compute((Number i, Number j) -> i.intValue() + j.intValue());

        assertThat(tuple, is(Tuple.of(1, 2, 3)));
    }

}
