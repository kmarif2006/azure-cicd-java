package com.example.cicd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/stopwatch")
public class StopwatchController {

    private final StopwatchService stopwatchService;

    public StopwatchController(StopwatchService stopwatchService) {
        this.stopwatchService = stopwatchService;
    }

    @GetMapping
    public StopwatchService.StopwatchState getStatus() {
        return stopwatchService.getStatus();
    }

    @PostMapping("/start")
    public StopwatchService.StopwatchState start() {
        return stopwatchService.start();
    }

    @PostMapping("/pause")
    public StopwatchService.StopwatchState pause() {
        return stopwatchService.pause();
    }

    @PostMapping("/reset")
    public StopwatchService.StopwatchState reset() {
        return stopwatchService.reset();
    }

    @PostMapping("/lap")
    public StopwatchService.Lap lap() {
        try {
            return stopwatchService.addLap();
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }
}
