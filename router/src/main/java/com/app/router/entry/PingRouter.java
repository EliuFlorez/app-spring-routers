package com.app.router.entry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ping_routers")
public class PingRouter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ip;

	public PingRouter(String ip) {
        this.ip = ip;
    }

    public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "ip", nullable = false)
	public String getIP() {
		return this.ip;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}
}
