package com.qunar.lvshichang.guava;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class FunctionTest {
    @Test
    public void predicate() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        Optional<Integer> find = Iterables.tryFind(list, new Predicate<Integer>() {
            public boolean apply(@Nullable Integer input) {
                return input != null && input >= 5;
            }
        });
        if (find.isPresent()) {
            System.out.println(find.get());
        }
        Iterable<Integer> filter = Iterables.filter(list, new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return input != null && input >= 3;
            }
        });
        for (Integer in : filter) {
            System.out.println(in);
        }
    }


    @Test
    public void predicate2() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        FluentIterable<Integer> filter = FluentIterable.from(list).filter(new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return input != null && input >= 4;
            }
        });
        ImmutableList<Integer> integers = filter.toList();
        System.out.println(integers);
    }

    @Test
    public void function() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> transform = Lists.transform(list, new Function<Integer, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Integer input) {
                return input != null ? input * 5 : null;
            }
        });
        for (Integer i : transform) {
            System.out.println(i);
        }
    }
    @Test
    public void consumer() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        FluentIterable.from(list).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

    }
}
