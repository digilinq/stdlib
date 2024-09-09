package com.eightbits.shared.stdlib.command;

import java.util.function.Function;

public class CommandBuilder<T, R> {

    private final Function<T, R> func;

    private CommandBuilder(Function<T, R> func) {
        this.func = func;
    }

    public static <T, R> CommandBuilder<T, R> of(Function<T, R> func) {
        return new CommandBuilder<>(func);
    }

    public <V> CommandBuilder<V, R> compose(Function<V, T> before) {
        return new CommandBuilder<>(before.andThen(func));
    }

    public <V> CommandBuilder<T, V> andThen(Function<R, V> after) {
        return new CommandBuilder<>(func.andThen(after));
    }

    public R execute(T t) {
        return func.apply(t);
    }
}
