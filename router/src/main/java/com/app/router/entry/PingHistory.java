package com.app.router.entry;

import java.util.Arrays;
import java.util.List;

public class PingHistory {
    private static final int MAX_HISTORY_SIZE = 48; // 24 horas / 0.5 horas (cada 30 minutos)

    private PingResult[] history = new PingResult[MAX_HISTORY_SIZE];
    private int index = 0;

    public void addResult(PingResult result) {
        history[index] = result;
        index = (index + 1) % MAX_HISTORY_SIZE; // Circular buffer para mantener los últimos 24 horas
    }

    public List<PingResult> getAllResults() {
        return Arrays.asList(history);
    }

    public PingResult getLatestResult() {
        if (index == 0) {
            return history[MAX_HISTORY_SIZE - 1];
        } else {
            return history[index - 1];
        }
    }
}
