package com.example.cicd;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StopwatchService {

    private long elapsedTime = 0;
    private long startTime = 0;
    private boolean running = false;
    private final List<Lap> laps = new ArrayList<>();

    public synchronized StopwatchState getStatus() {
        return new StopwatchState(
            getCurrentTime(),
            running,
            new ArrayList<>(laps)
        );
    }

    public synchronized StopwatchState start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
        return getStatus();
    }

    public synchronized StopwatchState pause() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            running = false;
        }
        return getStatus();
    }

    public synchronized StopwatchState reset() {
        elapsedTime = 0;
        startTime = 0;
        running = false;
        laps.clear();
        return getStatus();
    }

    public synchronized Lap addLap() {
        if (!running) {
            throw new IllegalStateException(
                "Start the stopwatch before recording a lap."
            );
        }

        Lap lap = new Lap(laps.size() + 1, getCurrentTime());
        laps.add(lap);
        return lap;
    }

    private long getCurrentTime() {
        return elapsedTime +
            (running ? System.currentTimeMillis() - startTime : 0);
    }

    public record StopwatchState(
        long elapsedMs,
        boolean running,
        List<Lap> laps
    ) {}

    public record Lap(
        int lapNumber,
        long elapsedMs
    ) {}
}
