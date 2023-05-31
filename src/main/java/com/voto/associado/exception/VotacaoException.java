package com.voto.associado.exception;

import org.springframework.http.HttpStatus;

public class VotacaoException extends RuntimeException{

    private final HttpStatus httpStatus;

    public VotacaoException(MensagemExceptionEnum mensagemExceptionEnum, HttpStatus httpStatus) {
        super(mensagemExceptionEnum.getMensagemDeErroDeNegocio(), null, true, false);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return this.httpStatus.value();
    }

}
