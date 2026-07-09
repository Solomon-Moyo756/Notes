package secure.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class NotesApplication {
	//UDEMY COURSE ON SPRING SECURITY: video: 63. Defining UserDetailsServiceImpl and UserDetailsImpl
	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}
}
