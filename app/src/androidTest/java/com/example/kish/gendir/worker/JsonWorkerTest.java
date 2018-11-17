package com.example.kish.gendir.worker;

import org.junit.Ignore;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class JsonWorkerTest {
    Logger log = Logger.getLogger("test");

    JsonWorker jsonWorker = new JsonWorker("http://localhost:8080");

    @Test
    @Ignore
    public void getAllPayments() {
        assertNotNull(jsonWorker.getAllPayments());

    }
}