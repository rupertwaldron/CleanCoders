package com.ruppyrup.episode34.iterator.loop.solution;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class SqintRR {
    public static void main(String[] args) throws InterruptedException {
        RRIterator<Integer> ints = new Integers();

        RRStream.of(ints)
                .map(x -> x * x)
                .filter(x -> x % 2 == 0)
                .map(x -> -x)
                .limit(25)
                .forEach(System.out::println);

    }
}

class RRStream<T> {
    private final RRIterator<T> iterator;
    private int limit = Integer.MAX_VALUE;

    public RRStream(RRIterator<T> iterator) {
        this.iterator = iterator;
    }

    public static <U> RRStream<U> of(RRIterator<U> iterator) {
        return new RRStream<>(iterator);
    }

    public RRStream<T> map(UnaryOperator<T> operation) {
        RRIterator<T> newIterator =  new RRIterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return operation.apply(iterator.next());
            }
        };

        return new RRStream<>(newIterator);
    }

    public RRStream<T> filter(Predicate<T> predicate) {
        RRIterator<T> newIterator =  new RRIterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                T next = iterator.next();
                while (!predicate.test(next))
                    next = iterator.next();

                return next;
            }
        };

        return new RRStream<>(newIterator);
    }

    public RRStream<T> limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public void forEach(Consumer<T> consumer) throws InterruptedException {
        int count = 0;
        while (iterator.hasNext() && count++ < limit) {
            Thread.sleep(1000);
            consumer.accept(iterator.next());
        }
    }
}