package getready;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import getready.service.InitDbService;

@SpringBootApplication
public class GetReadyApplication implements CommandLineRunner {
	
	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(GetReadyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Starting...");
//		initDbService.addUser();
//		initDbService.addLabels();
//		initDbService.addQuestions();
		System.out.println("Added");
		
	}

}
