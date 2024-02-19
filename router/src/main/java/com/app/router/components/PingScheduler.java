package com.app.router.components;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.router.entry.PingHistory;
import com.app.router.entry.PingResult;
import com.app.router.entry.PingRouter;
import com.app.router.repositories.PingRouterRepository;

@Component
public class PingScheduler {
    @Autowired
    private PingRouterRepository pingRouterRepository;

    private Map<String, PingHistory> pingCache = new HashMap<>();

    @Scheduled(fixedRate = 30 * 60 * 1000) // Ejecutar cada 30 minutos
    public void performPing() {
        // Iterar sobre todos los dispositivos y realizar ping
        for (String ipAddress : getDeviceIPsFromDatabase()) {
            PingResult pingResult = pingDevice(ipAddress);
            updatePingCache(ipAddress, pingResult);
        }
    }

    public Map<String, PingHistory> getPingCache() {
        return pingCache;
    }
    
    private PingResult pingDevice(String ipAddress) {
        try {
            System.out.println("Procesando IP: " + ipAddress);
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            long startTime = System.currentTimeMillis();
            boolean isReachable = inetAddress.isReachable(5000); // Timeout de 5 segundos
            long endTime = System.currentTimeMillis();
            long latency = endTime - startTime;

            return new PingResult(isReachable, latency);
        } catch (Exception e) {
            e.printStackTrace();
            return new PingResult(false, -1);
        }
    }

    private void updatePingCache(String ipAddress, PingResult pingResult) {
        PingHistory history = pingCache.getOrDefault(ipAddress, new PingHistory());
        history.addResult(pingResult);
        pingCache.put(ipAddress, history);
    }

    // Método ficticio para obtener las direcciones IP de los dispositivos desde la base de datos
    private String[] getDeviceIPsFromDatabase() {
        List<String> ips = pingRouterRepository.findAll().stream()
            .map(PingRouter::getIP)
            .collect(Collectors.toList());
        
        return ips.toArray(new String[0]);
    }
}
