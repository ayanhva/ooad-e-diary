package ooad.project.ediary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EDiaryApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EDiaryApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}