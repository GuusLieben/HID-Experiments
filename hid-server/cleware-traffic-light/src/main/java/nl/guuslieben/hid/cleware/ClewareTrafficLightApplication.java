package nl.guuslieben.hid.cleware;

import nl.guuslieben.hid.std.configuration.EnableHidServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHidServer
@SpringBootApplication
public class ClewareTrafficLightApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClewareTrafficLightApplication.class, args);
	}
}
