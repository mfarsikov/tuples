package org.comprehension.tuple;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class QuintetTest {

    @Test
    public void of() throws Exception {
        Quintet<Integer, Integer, Integer, Integer, Integer> tuple = Quintet.of(1, 2, 3, 4, 5);
        Assert.assertThat(tuple.getFirst(), Matchers.is(1));
        Assert.assertThat(tuple.getSecond(), Matchers.is(2));
        Assert.assertThat(tuple.getThird(), Matchers.is(3));
        Assert.assertThat(tuple.getFourth(), Matchers.is(4));
        Assert.assertThat(tuple.getFifth(), Matchers.is(5));
    }

    @Test
    public void flatmap() throws Exception {
        List<String> list = Stream.of(Tuple.of(1, 2, 3, 4, 5))
                                  .flatMap(Quintet.flat(Object::toString,
                                                        Object::toString,
                                                        Object::toString,
                                                        Object::toString,
                                                        Object::toString))
                                  .collect(Collectors.toList());

        Assert.assertThat(list, Matchers.hasSize(5));
        Assert.assertThat(list, Matchers.contains("1", "2", "3", "4", "5"));
    }
}
