package com.app.router.repositories;

import com.app.router.entry.PingRouter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingRouterRepository extends JpaRepository<PingRouter, Long> {
}
