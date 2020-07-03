package br.com.codenation.model;

import java.time.LocalDate;
import java.util.Objects;

public class Time {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String coruUniformeSecundario;
    private Jogador capitao;

    @java.lang.Override
    public java.lang.String toString() {
        return "Time{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", corUniformePrincipal='" + corUniformePrincipal + '\'' +
                ", coruUniformeSecundario='" + coruUniformeSecundario + '\'' +
                ", capitao=" + capitao +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Time time = (Time) object;
        return id.equals(time.id);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
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

    public Jogador getCapitao() {
        return capitao;
    }

    public void setCapitao(Jogador capitao) {
        this.capitao = capitao;
    }

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String coruUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.coruUniformeSecundario = coruUniformeSecundario;
    }
}