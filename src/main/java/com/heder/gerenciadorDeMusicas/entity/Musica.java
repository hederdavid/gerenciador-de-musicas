package com.heder.gerenciadorDeMusicas.entity;


import com.heder.gerenciadorDeMusicas.model.DadosMusica;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String musica;
    @ManyToOne
    private Artista artista;
    @ManyToOne
    private Album album;
    private int duracao;
    private String url;
    private String genero;

    public Musica(DadosMusica dadosMusica) {
        this.musica = dadosMusica.nomeMusica();
        this.duracao = dadosMusica.duracao();
        this.url = dadosMusica.url();
        this.genero = String.valueOf(dadosMusica.genero());
    }

    public Musica() {}
}
