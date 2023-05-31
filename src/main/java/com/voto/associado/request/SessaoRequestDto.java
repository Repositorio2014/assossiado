package com.voto.associado.request;

import java.time.LocalDateTime;

public class SessaoRequestDto {

    private LocalDateTime dataFechamento;

    public SessaoRequestDto() {
    }

    public SessaoRequestDto(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
