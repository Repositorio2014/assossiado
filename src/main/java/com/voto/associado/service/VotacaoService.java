package com.voto.associado.service;

import com.voto.associado.exception.MensagemExceptionEnum;
import com.voto.associado.exception.VotacaoException;
import com.voto.associado.model.PautaModel;
import com.voto.associado.model.VotacaoModel;
import com.voto.associado.repository.PautaRepository;
import com.voto.associado.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Cacheable(value = "votacao", key = "#votacao.id")
public class VotacaoService {

    @Value("60")
    private Integer tempoSessaoPadrao;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotacaoRepository sessaoRepository;

    public Optional<PautaModel> getPauta(Long id) {
        return pautaRepository.findById(id);
    }

    @Transactional
    public void iniciarVotacao(Long idPauta, LocalDateTime dataFechamento) {
        PautaModel pauta = getPauta(idPauta).orElseThrow(() -> new VotacaoException(MensagemExceptionEnum.PAUTA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND));

        if(Objects.requireNonNull(getVotacao(pauta)).isPresent()){
            throw new VotacaoException(MensagemExceptionEnum.SESSAO_JA_EXISTE, HttpStatus.CONFLICT);
        }

        criaSessaoVotacao(pauta, dataFechamento);
    }

    private void criaSessaoVotacao(PautaModel pauta, LocalDateTime dataFechamento) {

        VotacaoModel votacao = new VotacaoModel();
        votacao.setDataAbertura(LocalDateTime.now());
        votacao.setDataFechamento(dataFechamento);
        votacao.setPauta(pauta);

        sessaoRepository.save(votacao);
    }


    private Optional<VotacaoModel> getVotacao(PautaModel pauta) {
        return sessaoRepository.findByPauta(pauta);
    }

    public Integer getTempoSessaoPadrao() {
        return tempoSessaoPadrao;
    }

}
