package com.eightbits.shared.stdlib.streams;

import java.util.function.Function;

/**
 * A class that wraps a function and allows to perform operations on it.
 * @param <T> the type of the input
 * @param <R> the type of the output
 * @since 1.0
 *           <p>
 *           Example:
 *           <pre>{@code
 *           Service<String, Integer> service = Service.of(Integer::parseInt);
 *           assertEquals(5, service.apply("5"));
 *           }</pre>
 *           <p>
 *           <pre>{@code
 *           Service<String, Integer> service = Service.of(Integer::parseInt)
 *           .compose(String::valueOf);
 *           assertEquals(5, service.apply(5));
 *           }</pre>
 *           <p>
 *           <pre>{@code
 *           Service<String, Integer> service = Service.of(Integer::parseInt)
 *           .andThen(x -> x + 1);
 *           assertEquals(6, service.apply("5"));
 *           }</pre>
 *           <p>
 *           <pre>{@code
 *           Service<String, Integer> service = Service.of(Integer::parseInt)
 *           .compose(String::valueOf)
 *           .andThen(x -> x + 1);
 *           assertEquals(6, service.apply(5));
 *           }</pre>
 *           <p>
 *           <pre>{@code
 *           Service<String, Integer> service = Service.of(Integer::parseInt)
 *           .compose(String::valueOf)
 *           .andThen(x -> x + 1)
 *           .compose(String::valueOf)
 *           .andThen(x -> x + 1);
 *           assertEquals(7, service.apply(5));
 *           }</pre>
 *           <p>
 */
public class Service<T, R> {

    /**
     * The function to wrap
     */
    private final Function<T, R> func;

    /**
     * Constructor
     * @param func the function to wrap
     */
    private Service(Function<T, R> func) {
        this.func = func;
    }

    /**
     * Factory method to create a new instance of Service
     * @param func the function to wrap
     * @return a new instance of Service
     */
    public static <T, R> Service<T, R> of(Function<T, R> func) {
        return new Service<>(func);
    }

    /**
     * Compose the function with another function
     * @param before the function to compose with
     * @return a new instance of Service
     */
    public <V> Service<V, R> compose(Function<V, T> before) {
        return new Service<>(before.andThen(func));
    }

    /**
     * Compose the function with another function
     * @param after the function to compose with
     * @return a new instance of Service
     */
    public <V> Service<T, V> andThen(Function<R, V> after) {
        return new Service<>(func.andThen(after));
    }

    /**
     * Apply the function to the input
     * @param t the input
     * @return the output
     */
    public R apply(T t) {
        return func.apply(t);
    }
}
