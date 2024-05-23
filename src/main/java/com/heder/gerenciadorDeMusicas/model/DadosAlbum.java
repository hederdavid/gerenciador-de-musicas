package com.heder.gerenciadorDeMusicas.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAlbum(@JsonAlias("title") String nome,
                         @JsonAlias("url") String url) {
}
