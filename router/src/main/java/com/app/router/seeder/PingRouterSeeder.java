package com.app.router.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.router.entry.PingRouter;
import com.app.router.repositories.PingRouterRepository;

@Component
public class PingRouterSeeder implements ApplicationRunner {
    @Autowired
    private PingRouterRepository pingRouterRepository; // Reemplaza "YourRepository" con el nombre de tu repositorio

    private String[] seederIPs = {
        "192.168.0.1",
        "8.8.8.8",
        "10.0.0.1",
    };

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Aquí puedes poblar la base de datos con tus datos de prueba
        // Ejemplo:
        for (String ip : seederIPs) {
            PingRouter entity = new PingRouter(ip);
            pingRouterRepository.save(entity);
        }
    }
}
