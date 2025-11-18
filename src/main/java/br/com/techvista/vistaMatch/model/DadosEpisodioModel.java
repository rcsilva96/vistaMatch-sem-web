package br.com.techvista.vistaMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodioModel(@JsonAlias("Title") String titulo,
                                 @JsonAlias("Episode") Integer numeroEpisodio,
                                 @JsonAlias("imdbRating") String avaliacao,
                                 @JsonAlias("Released") String dataLancamento) {
}
