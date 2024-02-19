package com.app.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.router.components.PingScheduler;
import com.app.router.entry.PingResult;
import com.app.router.entry.PingHistory;
import com.app.router.exceptions.DeviceNotFoundException;

@RestController
@RequestMapping("/ping")
public class PingController {
    @Autowired
    private PingScheduler pingScheduler; // Suponiendo que tienes una instancia del PingScheduler en tu aplicación

    @GetMapping("/routers")
    public Map<String, Long> getAllDevicesLatency() {
        Map<String, Long> devicesLatency = new HashMap<>();
        
        // Accede a la caché de pings y extrae la última latencia registrada de cada dispositivo
        for (Map.Entry<String, PingHistory> entry : pingScheduler.getPingCache().entrySet()) {
            PingHistory history = entry.getValue();
            PingResult latestResult = history.getLatestResult();
            if (latestResult != null) {
                devicesLatency.put(entry.getKey(), latestResult.getLatency());
            }
        }
        
        return devicesLatency;
    }

    @GetMapping("/routers/{deviceIp}")
    public List<PingResult> getDeviceHistory(@PathVariable String deviceIp) {
        PingHistory history = pingScheduler.getPingCache().get(deviceIp);
        if (history != null) {
            return history.getAllResults();
        } else {
            // Manejar el caso en el que el dispositivo no se encuentra en la caché
            throw new DeviceNotFoundException("Device with IP " + deviceIp + " not found");
        }
    }
}
