package com.voto.associado.request;

import com.voto.associado.model.MesagemEnum;

import javax.validation.constraints.NotNull;

public class VotoRequestDto {

    @NotNull(message = "O CPF é obrigatório.")
    private String cpf;

    @NotNull(message = "Mensagem de voto deve ser SIM ou NAO")
    private MesagemEnum mensagem;

    public VotoRequestDto(String cpf, MesagemEnum mensagem) {
        this.cpf = cpf;
        this.mensagem = mensagem;
    }

    public VotoRequestDto() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public MesagemEnum getMensagem() {
        return mensagem;
    }

    public void setMensagem(MesagemEnum mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "VotoRequestDto{" +
                "cpf='" + cpf + '\'' +
                ", mensagem=" + mensagem +
                '}';
    }
}
