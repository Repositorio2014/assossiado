package com.voto.associado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voto.associado.model.VotoModel;
import com.voto.associado.request.VotoRequestDto;
import com.voto.associado.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/voto")
public class VotoController {

    private static final Logger logger = LoggerFactory.getLogger(VotoController.class);

    @Autowired
    private VotoService votoService;
    private ObjectMapper objectMapper;

    @PostMapping("/{idPauta}/votar")
    public ResponseEntity votar(@PathVariable("idPauta") Long idPauta, @RequestBody VotoRequestDto voto) throws Exception {
        objectMapper = new ObjectMapper();

        votoService.votar(idPauta, objectMapper.convertValue(voto, VotoModel.class));
        logger.info("Voto registrado com sucesso!");

        return ResponseEntity.ok().build();
    }

}
