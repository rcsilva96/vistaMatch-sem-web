package br.com.techvista.vistaMatch.principal;

import br.com.techvista.vistaMatch.model.DadosEpisodioModel;
import br.com.techvista.vistaMatch.model.DadosSerieModel;
import br.com.techvista.vistaMatch.model.DadosTemporadaModel;
import br.com.techvista.vistaMatch.model.EpisodioModel;
import br.com.techvista.vistaMatch.service.ConsumoAPI;
import br.com.techvista.vistaMatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


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

        for (int i = 0; i < temporadas.size(); i++) {

            List<DadosEpisodioModel> episodiosTemporada = temporadas.get(i).episodios();
            for(int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        /*System.out.println("=================================================================================");

        List<String> nomes = Arrays.asList("Renato", "Mayra", "Cinthya", "Gabriel", "Ezequiel", "Carol");

        nomes.stream()
                .sorted() // Organiza os itens de 'nomes' em ordem alfabética
                //.limit(2) // Limita a quantidade de itens em 2
                .filter(n -> n.length() == 5) // Filtra nomes com 5 exatos caracteres
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);*/

        List<DadosEpisodioModel> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .toList(); // Lista imutavel, não permite adicionar ou remover itens, para alterar, use "toList()"

        System.out.println("\n Top 5 episódios");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodioModel::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        System.out.println("=========================================================================");

        List<EpisodioModel> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new EpisodioModel(t.numeroTemporada(), d))
                )
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("=========================================================================");
        System.out.println("A partir de que ano você quer assistir os episódios?");
        var ano = sc.nextInt();
        sc.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episodio: " + e.getTitulo() +
                                " Data de lançamento: " + e.getDataLancamento().format(formatter)
                ));
    }

}
