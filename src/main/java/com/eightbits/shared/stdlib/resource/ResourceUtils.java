package com.eightbits.shared.stdlib.resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ResourceUtils {

    private static final ObjectMapper OBJECT_MAPPER = null;

    private static String readAsString(InputStream in, Charset cs) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, cs))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private static String readAsString1(InputStream in, Charset cs) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, cs))) {
            return reader.lines().collect(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append
            ).toString();
        }
    }

    static String readAsString2(InputStream in, Charset cs) throws IOException {
        int ch;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, cs))) {
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        }
    }

    static String readAsString3(InputStream in, Charset cs) throws IOException {
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            resultStream.write(buffer, 0, bytesRead);
        }
        return resultStream.toString(cs);
    }

    private static URI getResourceUri(String resourceName) throws URISyntaxException {
        return getResourceUrl(resourceName).toURI();
    }

    private static URL getResourceUrl(String resourceName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        if (url == null) {
            throw new IllegalArgumentException(resourceName + " not found!");
        }
        return url;
    }

    private <T> T readAsType(String resourceName, Class<T> type) throws IOException {
        return OBJECT_MAPPER.readValue(getResourceUrl(resourceName), type);
    }

    private static <T> T readAsType(InputStream in, Class<T> type) throws IOException {
        return OBJECT_MAPPER.readValue(in, type);
    }

    interface TypeResource<T> {
        Class<T> getType();

        InputStream getResourceName();

        default T readApiModel() throws IOException {
            return readAsType(getResourceName(), getType());
        }
    }
}
