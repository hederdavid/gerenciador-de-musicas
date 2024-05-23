package com.heder.gerenciadorDeMusicas.entity;

import com.heder.gerenciadorDeMusicas.model.DadosArtista;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;

@Data
@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String nome;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Album> albuns;
    private String url;

    public Artista(DadosArtista dadosArtista) {
        this.nome = dadosArtista.nome();
        this.url = dadosArtista.url();
    }

    public Artista() {

    }
}
