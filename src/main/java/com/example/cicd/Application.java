
package com.example.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Chrono - Stopwatch</title>
                <style>
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }

                    body {
                        min-height: 100vh;
                        font-family: 'Segoe UI', Arial, sans-serif;
                        background:
                            radial-gradient(circle at 15% 20%, #263d70 0, transparent 35%),
                            radial-gradient(circle at 85% 80%, #35245e 0, transparent 35%),
                            #0b1020;
                        color: #ffffff;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        padding: 24px;
                    }

                    .app {
                        width: 100%;
                        max-width: 620px;
                        background: rgba(255,255,255,0.07);
                        border: 1px solid rgba(255,255,255,0.12);
                        border-radius: 28px;
                        padding: 36px;
                        box-shadow: 0 25px 80px rgba(0,0,0,0.4);
                        backdrop-filter: blur(18px);
                    }

                    .header {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        margin-bottom: 42px;
                    }

                    .logo {
                        font-size: 23px;
                        font-weight: 800;
                        letter-spacing: 2px;
                    }

                    .logo span {
                        color: #8b9cff;
                    }

                    .status {
                        font-size: 12px;
                        color: #aeb9d1;
                        background: rgba(255,255,255,0.08);
                        padding: 9px 14px;
                        border-radius: 30px;
                    }

                    .status-dot {
                        display: inline-block;
                        width: 7px;
                        height: 7px;
                        border-radius: 50%;
                        background: #a0a9bb;
                        margin-right: 7px;
                    }

                    .status.running .status-dot {
                        background: #27e0a3;
                        box-shadow: 0 0 10px #27e0a3;
                    }

                    .heading {
                        text-align: center;
                        color: #aab5ce;
                        font-size: 12px;
                        letter-spacing: 5px;
                        text-transform: uppercase;
                    }

                    .timer {
                        text-align: center;
                        margin: 28px 0 35px;
                        font-size: clamp(43px, 10vw, 76px);
                        font-weight: 700;
                        letter-spacing: 2px;
                        font-variant-numeric: tabular-nums;
                        white-space: nowrap;
                    }

                    .timer .milliseconds {
                        color: #8b9cff;
                    }

                    .controls {
                        display: flex;
                        justify-content: center;
                        gap: 12px;
                        flex-wrap: wrap;
                    }

                    button {
                        border: none;
                        border-radius: 14px;
                        padding: 15px 24px;
                        font-size: 14px;
                        font-weight: 700;
                        cursor: pointer;
                        transition: all .2s ease;
                    }

                    button:hover {
                        transform: translateY(-3px);
                        filter: brightness(1.12);
                    }

                    button:active {
                        transform: scale(.96);
                    }

                    .start {
                        background: #27e0a3;
                        color: #071b17;
                        min-width: 115px;
                    }

                    .pause {
                        background: #ffbd59;
                        color: #291900;
                    }

                    .reset {
                        background: rgba(255,255,255,0.12);
                        color: white;
                    }

                    .lap {
                        background: #7187ff;
                        color: white;
                    }

                    .section {
                        margin-top: 40px;
                    }

                    .section-header {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        padding-bottom: 15px;
                        border-bottom: 1px solid rgba(255,255,255,0.12);
                    }

                    .section-header h3 {
                        font-size: 15px;
                    }

                    .lap-count {
                        color: #aab5ce;
                        font-size: 12px;
                    }

                    .lap-list {
                        margin-top: 10px;
                        max-height: 220px;
                        overflow-y: auto;
                    }

                    .lap-row {
                        display: flex;
                        justify-content: space-between;
                        padding: 14px 8px;
                        border-bottom: 1px solid rgba(255,255,255,0.07);
                        color: #d5ddef;
                        font-size: 14px;
                    }

                    .lap-row:first-child {
                        color: #27e0a3;
                    }

                    .empty {
                        text-align: center;
                        color: #77839d;
                        padding: 25px 0;
                        font-size: 13px;
                    }

                    .footer {
                        text-align: center;
                        margin-top: 35px;
                        color: #66728d;
                        font-size: 11px;
                        letter-spacing: 1px;
                    }

                    @media (max-width: 480px) {
                        .app { padding: 25px 16px; }
                        .header { margin-bottom: 35px; }
                        button { padding: 13px 17px; }
                    }
                </style>
            </head>

            <body>
                <main class="app">
                    <header class="header">
                        <div class="logo">CHRONO<span>.</span></div>
                        <div class="status" id="status">
                            <span class="status-dot"></span>
                            READY
                        </div>
                    </header>

                    <div class="heading">Precision Stopwatch</div>

                    <div class="timer" id="timer">
                        00:00:00<span class="milliseconds">.00</span>
                    </div>

                    <div class="controls">
                        <button class="start" onclick="startTimer()">▶ Start</button>
                        <button class="pause" onclick="pauseTimer()">Ⅱ Pause</button>
                        <button class="reset" onclick="resetTimer()">↻ Reset</button>
                        <button class="lap" onclick="recordLap()">⚑ Lap</button>
                    </div>

                    <section class="section">
                        <div class="section-header">
                            <h3>Lap History</h3>
                            <span class="lap-count" id="lapCount">0 LAPS</span>
                        </div>

                        <div class="lap-list" id="lapList">
                            <div class="empty">Your lap records will appear here.</div>
                        </div>
                    </section>

                    <footer class="footer">
                        BUILT WITH JAVA 17 · SPRING BOOT
                    </footer>
                </main>

                <script>
                    let elapsed = 0;
                    let startTime = 0;
                    let running = false;
                    let interval = null;
                    let lapNumber = 0;

                    function formatTime(ms) {
                        const total = Math.floor(ms);
                        const hours = Math.floor(total / 3600000);
                        const minutes = Math.floor((total % 3600000) / 60000);
                        const seconds = Math.floor((total % 60000) / 1000);
                        const centiseconds = Math.floor((total % 1000) / 10);

                        return {
                            main: [hours, minutes, seconds]
                                .map(n => String(n).padStart(2, '0')).join(':'),
                            fraction: '.' + String(centiseconds).padStart(2, '0')
                        };
                    }

                    function currentTime() {
                        return elapsed + (running ? performance.now() - startTime : 0);
                    }

                    function updateDisplay() {
                        const time = formatTime(currentTime());
                        document.getElementById('timer').innerHTML =
                            time.main + '<span class="milliseconds">' +
                            time.fraction + '</span>';
                    }

                    function setStatus(value) {
                        const status = document.getElementById('status');
                        status.classList.toggle('running', value === 'RUNNING');
                        status.innerHTML =
                            '<span class="status-dot"></span>' + value;
                    }

                    function startTimer() {
                        if (running) return;

                        startTime = performance.now();
                        running = true;
                        interval = setInterval(updateDisplay, 10);
                        setStatus('RUNNING');
                    }

                    function pauseTimer() {
                        if (!running) return;

                        elapsed += performance.now() - startTime;
                        running = false;
                        clearInterval(interval);
                        interval = null;
                        updateDisplay();
                        setStatus('PAUSED');
                    }

                    function resetTimer() {
                        pauseTimer();
                        elapsed = 0;
                        lapNumber = 0;
                        document.getElementById('lapList').innerHTML =
                            '<div class="empty">Your lap records will appear here.</div>';
                        document.getElementById('lapCount').textContent = '0 LAPS';
                        updateDisplay();
                        setStatus('READY');
                    }

                    function recordLap() {
                        if (!running) return;

                        lapNumber++;
                        const time = formatTime(currentTime());
                        const value = time.main + time.fraction;

                        const row = document.createElement('div');
                        row.className = 'lap-row';

                        const name = document.createElement('span');
                        name.textContent = 'Lap ' + lapNumber;

                        const result = document.createElement('span');
                        result.textContent = value;

                        row.append(name, result);

                        const list = document.getElementById('lapList');
                        if (lapNumber === 1) list.innerHTML = '';
                        list.prepend(row);

                        document.getElementById('lapCount').textContent =
                            lapNumber + (lapNumber === 1 ? ' LAP' : ' LAPS');
                    }

                    updateDisplay();
                </script>
            </body>
            </html>
            """;
    }
}
