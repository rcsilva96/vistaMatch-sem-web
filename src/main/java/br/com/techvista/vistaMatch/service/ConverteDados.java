package br.com.techvista.vistaMatch.service;

import br.com.techvista.vistaMatch.model.DadosSerieModel;
import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper =  new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return mapper.readValue(json, classe);
    }
}
