package com.eightbits.shared.stdlib.streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithTest {

    @Test
    void should_map_to_string() {
        With<String> result = With.value(5).map(String::valueOf);
        assertEquals("5", result.get());
    }

    @Test
    void should_perform_an_operation() {
        Integer value = 5;
        With<Integer> result = With.value(5).perform(x -> x + 1);
        assertEquals(6, result.get());
    }

    @Test
    void should_return_default_value() {
        With<Integer> result = With.value(null);
        assertEquals(5, result.orElse(5));
    }




}
