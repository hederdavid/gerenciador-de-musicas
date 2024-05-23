package com.heder.gerenciadorDeMusicas.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Genero(@JsonAlias("tags") String genero) {

}
