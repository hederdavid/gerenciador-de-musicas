package com.heder.gerenciadorDeMusicas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String album;
    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;
    @ManyToOne()
    private Artista artista;
    private String url;
}
