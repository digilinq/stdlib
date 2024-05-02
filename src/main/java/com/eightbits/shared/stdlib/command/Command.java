package com.eightbits.shared.stdlib.command;

import java.util.function.Function;

public class Command<T, R> {

    private final Function<T, R> func;

    private Command(Function<T, R> func) {
        this.func = func;
    }

    public static <T, R> Command<T, R> of(Function<T, R> func) {
        return new Command<>(func);
    }

    public <V> Command<V, R> compose(Function<V, T> before) {
        return new Command<>(before.andThen(func));
    }

    public <V> Command<T, V> andThen(Function<R, V> after) {
        return new Command<>(func.andThen(after));
    }

    public R execute(T t) {
        return func.apply(t);
    }
}
