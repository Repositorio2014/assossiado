package com.voto.associado.service;

import com.voto.associado.exception.MensagemExceptionEnum;
import com.voto.associado.exception.VotacaoException;
import com.voto.associado.model.PautaModel;
import com.voto.associado.model.VotacaoModel;
import com.voto.associado.model.VotoModel;
import com.voto.associado.repository.PautaRepository;
import com.voto.associado.repository.VotacaoRepository;
import com.voto.associado.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotoService {

    private final PautaRepository pautaRepository;
    private final VotacaoRepository sessaoRepository;
    private final VotoRepository votoRepository;

    public VotoService(PautaRepository pautaRepository, VotacaoRepository sessaoRepository, VotoRepository votoRepository) {
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
    }

    public Optional<PautaModel> getPauta(Long id) {
        return pautaRepository.findById(id);
    }

    private Optional<VotacaoModel> getVotacao(PautaModel pauta) {
        return sessaoRepository.findByPauta(pauta);
    }

    @Transactional
    public void votar(Long idPauta, VotoModel voto) throws Exception {
        VotacaoModel sessaoVotacao = getVotacao(getPauta(idPauta)
                .orElseThrow(() -> new VotacaoException(MensagemExceptionEnum.PAUTA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND)))
                .orElseThrow(() -> new VotacaoException(MensagemExceptionEnum.SESSAO_NAO_ENCONTRADA, HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataFechamento())) {
            throw new VotacaoException(MensagemExceptionEnum.SESSAO_FECHADA, HttpStatus.BAD_REQUEST);
        }

        if(voto.getCpf() != null) {

            //Exemplo de consumindo API externa https://user-info.herokuapp
            URL url = new URL("https://viacep.com.br/ws/58075412/json");
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String cep = "";
            StringBuilder jsonCep = new StringBuilder();

            while((cep = br.readLine()) != null){
                jsonCep.append(cep);
            }

            System.out.println(jsonCep.toString());

            voto.setVotacao(sessaoVotacao);
            voto.setDataHora(LocalDateTime.now());

            if (votoRepository.existsByVotacaoAndCpf(sessaoVotacao, voto.getCpf())) {
                throw new VotacaoException(MensagemExceptionEnum.VOTO_JA_REGISTRADO, HttpStatus.BAD_REQUEST);
            }

            votoRepository.save(voto);
        }
    }

}
