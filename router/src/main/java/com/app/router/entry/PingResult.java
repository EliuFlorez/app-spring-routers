package com.app.router.entry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ping_results")
public class PingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private boolean isReachable;
    private long latency;

    public PingResult(boolean isReachable, long latency) {
        this.isReachable = isReachable;
        this.latency = latency;
    }

    @Column(name = "is_reachable", nullable = false)
    public boolean getIsReachable() {
        return isReachable;
    }

    @Column(name = "latency", nullable = false)
    public long getLatency() {
        return latency;
    }
}
