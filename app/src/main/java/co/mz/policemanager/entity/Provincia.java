package co.mz.policemanager.entity;

import java.io.Serializable;

public class Provincia implements Serializable {

    private Long id;
    private String provinciaCode;
    private String sigla;
    private String nome;

    public Provincia() {
    }

    public Provincia(Long id, String provinciaCode, String sigla, String nome) {
        this.id = id;
        this.provinciaCode = provinciaCode;
        this.sigla = sigla;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinciaCode() {
        return provinciaCode;
    }

    public void setProvinciaCode(String provinciaCode) {
        this.provinciaCode = provinciaCode;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", provinciaCode='" + provinciaCode + '\'' +
                ", sigla='" + sigla + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
