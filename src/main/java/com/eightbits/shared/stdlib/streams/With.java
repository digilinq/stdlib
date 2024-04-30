package com.eightbits.shared.stdlib.streams;

import java.util.function.Function;

/**
 * A class that wraps a value and allows to perform operations on it.
 * @param <T> the type of the value
 *           <p>
 *           Example:
 *           <pre>{@code
 *           With<String> result = With.value(5).map(String::valueOf);
 *           assertEquals("5", result.get());
 *           }</pre>
 *           <p>
 *           <pre>{@code
 *           Integer value = 5;
 *           With<Integer> result = With.value(5).perform(x -> x + 1);
 *           assertEquals(6, result.get());
 *           }</pre>
 *           <p>
 *           <pre>{@code
 *           With<Integer> result = With.value(null);
 *           assertEquals(5, result.orElse(5));
 *           }</pre>
 *           <p>
 */
public class With<T> {
    private final T value;

    private With(T value) {
        this.value = value;
    }

    public static <T> With<T> value(T value) {
        return new With<>(value);
    }

    public <R> With<R> perform(Function<T, R> f) {
        return With.value(f.apply(value));
    }

    public <R> With<R> map(Function<T, R> mapper) {
        return With.value(mapper.apply(value));
    }

    public T orElse(T other) {
        if (value == null)
            return other;
        return value;
    }

    public T get() {
        return value;
    }

    public <R> R get(Function<T, R> mapper) {
        return mapper.apply(value);
    }
}
