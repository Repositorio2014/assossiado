package com.voto.associado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pauta")
public class PautaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pauta;

    public PautaModel() {
    }

    public PautaModel(Long id, String pauta) {
        this.id = id;
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    @Override
    public String toString() {
        return "PautaModel{" +
                "id=" + id +
                ", pauta='" + pauta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaModel that = (PautaModel) o;
        return Objects.equals(id, that.id) && Objects.equals(pauta, that.pauta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta);
    }
}
