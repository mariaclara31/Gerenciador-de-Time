package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Time {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String coruUniformeSecundario;
    private List<Jogador> jogadores;
    private Jogador capitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario)  {
        this.setId(id);
        this.setNome(nome);
        this.setDataCriacao(dataCriacao);
        this.setCorUniformePrincipal(corUniformePrincipal);
        this.setCorUniformeSecundario(corUniformeSecundario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCoruUniformeSecundario() {
        return coruUniformeSecundario;
    }

    public void setCoruUniformeSecundario(String coruUniformeSecundario) {
        this.coruUniformeSecundario = coruUniformeSecundario;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void addJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    public Jogador getCapitao() {
        return capitao;
    }

    public void setCapitao(Jogador capitao) {
        this.capitao = capitao;
    }

    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Time other = (Time) obj;
        if(!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if(!Objects.equals(this.corUniformePrincipal, other.corUniformePrincipal)) {
            return false;
        }
        if(!Objects.equals(this.coruUniformeSecundario, other.coruUniformeSecundario)) {
            return false;
        }
        if(!Objects.equals(this.id, other.id)) {
            return false;
        }
        if(!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return true;
    }
}