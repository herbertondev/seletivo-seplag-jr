package br.com.projetoseplagjr.dto;

import java.io.InputStream;

public class FotoResponseDTO {

    private String nomePessoa;
    private InputStream imagem;

    public FotoResponseDTO(String nomePessoa, InputStream imagem) {
        this.nomePessoa = nomePessoa;
        this.imagem = imagem;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public InputStream getImagem() {
        return imagem;
    }
}
