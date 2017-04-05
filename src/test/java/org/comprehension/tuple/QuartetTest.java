package org.comprehension.tuple;

import java.util.stream.Stream;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuartetTest {
    @Test
    public void mapToQuintet() throws Exception {
        Quintet<Integer, Integer, Integer, Integer, Integer> tuple =
                Stream.of(Quartet.of(1, 2, 3, 4))
                      .map(Quartet.mapToQuintet((one, two, three, four) -> one + two + three + four))
                      .findAny()
                      .get();

        assertThat(tuple, is(Quintet.of(1, 2, 3, 4, 10)));
    }
}
