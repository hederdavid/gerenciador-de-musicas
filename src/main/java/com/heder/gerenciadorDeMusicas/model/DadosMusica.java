package com.heder.gerenciadorDeMusicas.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMusica(@JsonAlias("name") String nomeMusica,
                          @JsonAlias("artist") DadosArtista artista,
                          @JsonAlias("album") DadosAlbum album,
                          @JsonAlias("duration") int duracao,
                          @JsonAlias("url") String url,
                          @JsonAlias("toptags") Genero genero) {

    private static double convertMillisToSeconds(long milliseconds) {
        // Converte milissegundos para segundos
        double seconds = milliseconds / 1000.0;
        return seconds;
    }
}
