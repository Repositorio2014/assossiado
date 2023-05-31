package com.voto.associado.repository;

import com.voto.associado.model.VotacaoModel;
import com.voto.associado.model.VotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<VotoModel, String> {
    Boolean existsByVotacaoAndCpf(VotacaoModel sessaoVotacao, String cpf);
}
