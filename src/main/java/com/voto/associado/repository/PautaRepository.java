package com.voto.associado.repository;

import com.voto.associado.model.PautaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaModel, Long> {
}
