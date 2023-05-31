package com.voto.associado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voto.associado.model.PautaModel;
import com.voto.associado.request.PautaRequestDto;
import com.voto.associado.response.PautaResponseDto;
import com.voto.associado.service.PautaService;
import com.voto.associado.service.VotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

    @Autowired
    private PautaService pautaService;

    private ObjectMapper objectMapper;


    @GetMapping
    public List<PautaResponseDto> getPautasComResultado() {
        logger.info("Consultando pautas...");
        return pautaService.getPautas().stream()
                .map(this::getPautaResponse)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/criarPauta", produces = "application/json")
    public ResponseEntity<PautaResponseDto> criarPauta(@RequestBody PautaRequestDto pautaRequest) {
        objectMapper = new ObjectMapper();
        logger.info("Criando pauta...");
        PautaModel pauta = objectMapper.convertValue(pautaRequest, PautaModel.class);
        pauta = pautaService.novaPauta(pauta);
        logger.info("Pauta criada com sucesso!");

        return ResponseEntity.ok(objectMapper.convertValue(pauta, PautaResponseDto.class))
                .status(HttpStatus.CREATED)
                .build();
    }

    private PautaResponseDto getPautaResponse(PautaModel pauta) {
        objectMapper = new ObjectMapper();
        PautaResponseDto pautaResponse = objectMapper.convertValue(pauta, PautaResponseDto.class);
        pautaResponse.setResultado(pautaService.resultado(pauta));

        return pautaResponse;
    }

}
