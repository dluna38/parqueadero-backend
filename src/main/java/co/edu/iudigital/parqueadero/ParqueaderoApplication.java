package co.edu.iudigital.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;
import java.util.logging.Logger;

@SpringBootApplication
public class ParqueaderoApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
		Logger.getGlobal().info(TimeZone.getDefault().getDisplayName());
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

}
