package br.com.techvista.vistaMatch.principal;

import br.com.techvista.vistaMatch.model.DadosSerieModel;
import br.com.techvista.vistaMatch.model.DadosTemporadaModel;
import br.com.techvista.vistaMatch.service.ConsumoAPI;
import br.com.techvista.vistaMatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public void mostraMenu(){

        final String ENDERECO = "https://www.omdbapi.com/?apikey=";
        final String API_KEY = "a762d6ff&t=";
        final String TEMPORADA = "&season=";

        Scanner sc = new Scanner(System.in);
        ConsumoAPI consumo = new ConsumoAPI();
        ConverteDados conversor = new ConverteDados();

        System.out.println("Bem-vindo ao vistaMatch!");

        System.out.println("Insira o nome da série que deseja buscar: ");
        String nomeSerie = sc.nextLine();

        var json = consumo.obterDados(ENDERECO + API_KEY + nomeSerie.replace(" ", "+"));
        DadosSerieModel dadosSerie = conversor.obterDados(json, DadosSerieModel.class);

        System.out.println(dadosSerie);

        List<DadosTemporadaModel> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {

            json = consumo.obterDados(ENDERECO + API_KEY + nomeSerie.replace(" ", "+") + TEMPORADA + i);
            DadosTemporadaModel dadosTemporada = conversor.obterDados(json, DadosTemporadaModel.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

    }

}
