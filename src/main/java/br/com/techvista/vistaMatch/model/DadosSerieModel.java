package br.com.techvista.vistaMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerieModel(@JsonAlias("Title") String titulo,
                              @JsonAlias("totalSeasons") Integer totalTemporadas,
                              @JsonAlias("imdbRating") String avaliacao) {

    // A anotação @JsonAlias me permite pegar o campo direto do json, como se fosse um apelido
    // CTRL + ALT + O
}
