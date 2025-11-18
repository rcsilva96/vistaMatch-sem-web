package br.com.techvista.vistaMatch;

import br.com.techvista.vistaMatch.model.DadosSerieModel;
import br.com.techvista.vistaMatch.service.ConsumoAPI;
import br.com.techvista.vistaMatch.service.ConverteDados;
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

        var consumoAPÌ = new ConsumoAPI();
        var json = consumoAPÌ.obterDados("https://www.omdbapi.com/?apikey=a762d6ff&t=chavo");
        System.out.println(json);

        ConverteDados conversor = new ConverteDados();

        DadosSerieModel dados = conversor.obterDados(json, DadosSerieModel.class);
        System.out.println(dados);

    }
}
