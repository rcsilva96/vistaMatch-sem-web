package br.com.techvista.vistaMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporadaModel(@JsonAlias("Season") Integer numeroTemporada,
                                  @JsonAlias("Episodes") List<DadosEpisodioModel> episodios) {
}
