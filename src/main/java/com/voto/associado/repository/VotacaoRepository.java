package com.voto.associado.repository;

import com.voto.associado.model.PautaModel;
import com.voto.associado.model.VotacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotacaoRepository extends JpaRepository<VotacaoModel, Long> {
    Optional<VotacaoModel> findByPauta(PautaModel pauta);
}
