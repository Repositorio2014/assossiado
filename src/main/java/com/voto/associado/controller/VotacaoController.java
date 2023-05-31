package com.voto.associado.controller;

import com.voto.associado.service.VotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/votacao")
public class VotacaoController {

    private static final Logger logger = LoggerFactory.getLogger(VotacaoController.class);

    @Autowired
    private VotacaoService votacaoService;

    @PostMapping(value = "/{idPauta}/iniciar-votacao", produces = "application/json")
    public ResponseEntity iniciarVotacao(@PathVariable("idPauta") Long idPauta) {
        logger.info("Iniciando votação...", idPauta);
        votacaoService.iniciarVotacao(idPauta, LocalDateTime.now().plusSeconds(votacaoService.getTempoSessaoPadrao()));
        logger.info("Sessão de votação iniciada com sucesso, o tempo de votação encerra em " + votacaoService.getTempoSessaoPadrao() + " segundos.");

        return ResponseEntity.ok().build();
    }

}
