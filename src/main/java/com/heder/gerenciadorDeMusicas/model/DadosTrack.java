package com.heder.gerenciadorDeMusicas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTrack(@JsonProperty("track") DadosMusica track) {
}
