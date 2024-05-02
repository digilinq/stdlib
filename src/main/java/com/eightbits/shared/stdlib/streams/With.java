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

    /**
     * Constructor
     * @param value the value to wrap
     */
    private With(T value) {
        this.value = value;
    }

    /**
     * Factory method to create a new instance of With
     * @param value the value to wrap
     * @return a new instance of With
     */
    public static <T> With<T> value(T value) {
        return new With<>(value);
    }

    /**
     * Perform an operation on the value
     * @param f the function to apply
     * @return a new instance of With
     */
    public <R> With<R> perform(Function<T, R> f) {
        return With.value(f.apply(value));
    }

    /**
     * Map the value to another value
     * @param mapper the function to apply
     * @return a new instance of With
     */
    public <R> With<R> map(Function<T, R> mapper) {
        return With.value(mapper.apply(value));
    }

    /**
     * Return the value if it is not null, otherwise return the other value
     * @param other the value to return if the value is null
     * @return the value if it is not null, otherwise return the other value
     */
    public T orElse(T other) {
        if (value == null)
            return other;
        return value;
    }

    /**
     * Get the value
     * @return the value
     */
    public T get() {
        return value;
    }

    /**
     * Get the value
     * @param mapper the function to apply
     * @return the value
     */
    public <R> R get(Function<T, R> mapper) {
        return mapper.apply(value);
    }
}
