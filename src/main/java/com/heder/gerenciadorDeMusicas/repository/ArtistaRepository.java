package com.heder.gerenciadorDeMusicas.repository;

import com.heder.gerenciadorDeMusicas.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistaRepository extends JpaRepository<Artista, UUID> {
}
