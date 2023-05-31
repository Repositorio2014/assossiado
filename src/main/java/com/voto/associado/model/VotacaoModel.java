package com.voto.associado.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

@Entity
@Table(name = "votacao")
public class VotacaoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name = "dataAbertura")
    private LocalDateTime dataAbertura;

    @Column(name = "dataFechamento")
    private LocalDateTime dataFechamento;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private PautaModel pauta;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "votacao", cascade = CascadeType.ALL)
    private Collection<VotoModel> votosModel = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VotacaoModel() {
    }

    public VotacaoModel(Long id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, PautaModel pauta, Collection<VotoModel> votosModel) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.pauta = pauta;
        this.votosModel = votosModel;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public PautaModel getPauta() {
        return pauta;
    }

    public void setPauta(PautaModel pauta) {
        this.pauta = pauta;
    }

    public Collection<VotoModel> getVotosModel() {
        return votosModel;
    }

    public void setVotosModel(Collection<VotoModel> votosModel) {
        this.votosModel = votosModel;
    }

    @Override
    public String toString() {
        return "VotacaoModel{" +
                "id=" + id +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                ", pauta=" + pauta +
                ", votosModel=" + votosModel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoModel that = (VotacaoModel) o;
        return Objects.equals(id, that.id) && Objects.equals(dataAbertura, that.dataAbertura) && Objects.equals(dataFechamento, that.dataFechamento) && Objects.equals(pauta, that.pauta) && Objects.equals(votosModel, that.votosModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataAbertura, dataFechamento, pauta, votosModel);
    }
}
