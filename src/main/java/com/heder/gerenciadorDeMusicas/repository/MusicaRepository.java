package com.heder.gerenciadorDeMusicas.repository;

import com.heder.gerenciadorDeMusicas.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}
