package br.com.techvista.vistaMatch;

import br.com.techvista.vistaMatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VistaMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VistaMatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Principal principal = new Principal();
        principal.mostraMenu();

    }
}
