package com.voto.associado.response;

import java.util.Map;

public class PautaResponseDto {

    private String id;

    private String pauta;

    private Map<String, Long> resultado;

    public PautaResponseDto() {
    }

    public PautaResponseDto(String id, String nome, Map<String, Long> resultado) {
        this.id = id;
        this.pauta = nome;
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String nome) {
        this.pauta = nome;
    }

    public Map<String, Long> getResultado() {
        return resultado;
    }

    public void setResultado(Map<String, Long> resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "PautaResponseDto{" +
                "id='" + id + '\'' +
                ", pauta='" + pauta + '\'' +
                ", resultado=" + resultado +
                '}';
    }
}
