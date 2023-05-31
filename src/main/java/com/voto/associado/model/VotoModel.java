package com.voto.associado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class VotoModel implements Serializable {

    @NotNull(message = "O campo CPF é obrigatório.")
    @Id
    private String cpf;

    @NotNull(message = "Informar mensagem: SIM-NAO")
    @Column(name = "mensagem")
    @Enumerated(EnumType.STRING)
    private MesagemEnum mensagem;

    @Column(name = "data")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_votacao")
    private VotacaoModel votacao;

    public VotoModel() {
    }

    public VotoModel(String cpf, MesagemEnum mensagem, LocalDateTime dataHora, VotacaoModel votacao) {
        this.cpf = cpf;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
        this.votacao = votacao;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public VotacaoModel getVotacao() {
        return votacao;
    }

    public void setVotacao(VotacaoModel votacao) {
        this.votacao = votacao;
    }

    @Override
    public String toString() {
        return "VotoModel{" +
                "cpf='" + cpf + '\'' +
                ", mensagem=" + mensagem +
                ", dataHora=" + dataHora +
                ", votacao=" + votacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoModel votoModel = (VotoModel) o;
        return Objects.equals(cpf, votoModel.cpf) && mensagem == votoModel.mensagem && Objects.equals(dataHora, votoModel.dataHora) && Objects.equals(votacao, votoModel.votacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, mensagem, dataHora, votacao);
    }


}
