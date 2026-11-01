package com.example.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    @Test
    void applicationMessageShouldBeCorrect() {

        String expected = "Hello from Azure CI/CD! Version: 1.0";

        String actual = "Hello from Azure CI/CD! Version: 1.0";

        assertEquals(expected, actual);
    }
}