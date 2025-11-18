package br.com.techvista.vistaMatch;

import br.com.techvista.vistaMatch.model.DadosEpisodioModel;
import br.com.techvista.vistaMatch.model.DadosSerieModel;
import br.com.techvista.vistaMatch.model.DadosTemporadaModel;
import br.com.techvista.vistaMatch.service.ConsumoAPI;
import br.com.techvista.vistaMatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class VistaMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VistaMatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Bem-vindo ao vistaMatch!");

        System.out.println("Insira o nome da série que deseja buscar: ");
        String nomeSerie = sc.nextLine();

        var consumoAPÌ = new ConsumoAPI();
        var json = consumoAPÌ.obterDados("https://www.omdbapi.com/?apikey=a762d6ff&t=" + nomeSerie);
        System.out.println(json);

        ConverteDados conversor = new ConverteDados();

        DadosSerieModel dados = conversor.obterDados(json, DadosSerieModel.class);
        System.out.println(dados);

        json = consumoAPÌ.obterDados("https://www.omdbapi.com/?apikey=a762d6ff&t=" + nomeSerie + "&season=3&episode=8");
        DadosEpisodioModel dadosEpisodio = conversor.obterDados(json, DadosEpisodioModel.class);
        System.out.println(dadosEpisodio);

        List<DadosTemporadaModel> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {

            json = consumoAPÌ.obterDados("https://www.omdbapi.com/?apikey=a762d6ff&t=" + nomeSerie + "&season=" + i);
            DadosTemporadaModel dadosTemporada = conversor.obterDados(json, DadosTemporadaModel.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

    }
}
