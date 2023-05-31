package com.voto.associado.service;

import com.voto.associado.exception.MensagemExceptionEnum;
import com.voto.associado.exception.VotacaoException;
import com.voto.associado.model.PautaModel;
import com.voto.associado.model.VotacaoModel;
import com.voto.associado.model.VotoModel;
import com.voto.associado.repository.PautaRepository;
import com.voto.associado.repository.VotacaoRepository;
import com.voto.associado.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Cacheable(value = "pautas", key = "#pauta.id")
public class PautaService {

    @Value("60")
    private Integer tempoSessaoPadrao;

    private final PautaRepository pautaRepository;
    private final VotacaoRepository votacaoRepository;
    private final VotoRepository votoRepository;

    public PautaService(PautaRepository pautaRepository, VotacaoRepository votacaoRepository, VotoRepository votoRepository) {
        this.pautaRepository = pautaRepository;
        this.votacaoRepository = votacaoRepository;
        this.votoRepository = votoRepository;
    }

    @CacheEvict(value = "pautas", allEntries = true)
    @Transactional
    public PautaModel novaPauta(PautaModel pauta) {
        pautaRepository.save(pauta);
        return pauta;
    }

    @Cacheable(value="pautas")
    public List<PautaModel> getPautas() {
        return pautaRepository.findAll();
    }

    public Optional<PautaModel> getPauta(Long id) {
        return pautaRepository.findById(id);
    }

    private Optional<VotacaoModel> getSessaoVotacao(PautaModel pauta) {
        return votacaoRepository.findByPauta(pauta);
    }

    @Transactional
    public void votar(Long idPauta, VotoModel voto) {
        VotacaoModel sessaoVotacao = getSessaoVotacao(getPauta(idPauta)
                .orElseThrow(() -> new VotacaoException(MensagemExceptionEnum.PAUTA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND)))
                .orElseThrow(() -> new VotacaoException(MensagemExceptionEnum.SESSAO_NAO_ENCONTRADA, HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataFechamento())) {
            throw new VotacaoException(MensagemExceptionEnum.SESSAO_FECHADA, HttpStatus.BAD_REQUEST);
        }

        voto.setVotacao(sessaoVotacao);
        voto.setDataHora(LocalDateTime.now());

        if(votoRepository.existsByVotacaoAndCpf(sessaoVotacao, voto.getCpf())) {
            throw new VotacaoException(MensagemExceptionEnum.VOTO_JA_REGISTRADO, HttpStatus.BAD_REQUEST);
        }

        votoRepository.save(voto);
    }

    public Map<String, Long> resultado(PautaModel pauta) {

        Collection<VotoModel> votos = getSessaoVotacao(pauta).isPresent() ? getSessaoVotacao(pauta).get().getVotosModel() : new ArrayList<>();

        Map<String, Long> result = new HashMap<>();
        result.put("SIM", votos.stream().filter(v -> v.getMensagem().toString().equalsIgnoreCase("SIM")).count());
        result.put("NAO", votos.stream().filter(v -> v.getMensagem().toString().equalsIgnoreCase("NAO")).count());

        return result;
    }

    public Integer getTempoSessaoPadrao() {
        return tempoSessaoPadrao;
    }

}
