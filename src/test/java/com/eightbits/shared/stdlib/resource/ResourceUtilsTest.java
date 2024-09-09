package com.eightbits.shared.stdlib.resource;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class ResourceUtilsTest {

    @Test
    void should_read_json_resource_using_string_builder() throws IOException {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("json/person.json")) {
            Consumer<String> printer = System.out::println;
            printer.accept(
                    ResourceUtils.readAsString2(in, StandardCharsets.UTF_8)
            );
        }
    }

    @Test
    void should_read_json_resource_using_byte_array() throws IOException {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("json/person.json")) {
            Consumer<String> printer = System.out::println;
            printer.accept(
                    ResourceUtils.readAsString3(in, StandardCharsets.UTF_8)
            );
        }
    }
}
