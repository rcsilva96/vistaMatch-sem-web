package br.com.techvista.vistaMatch.service;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

}
