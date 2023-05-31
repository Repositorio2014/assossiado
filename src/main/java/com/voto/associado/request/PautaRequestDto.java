package com.voto.associado.request;

import javax.validation.constraints.NotBlank;

public class PautaRequestDto {
    @NotBlank(message = "Nome é obrigatótio")
    private String pauta;

    public PautaRequestDto() {}

    public PautaRequestDto(String nome) {
        this.pauta = nome;
    }

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String nome) {
        this.pauta = nome;
    }


}
