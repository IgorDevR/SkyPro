package hw.skypro;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyproApplication {

	public static void main(String[] args) {

		IntegerList integerList = new IntegerListImpl(5);
//		SpringApplication.run(SkyproApplication.class, args);
	}

}
