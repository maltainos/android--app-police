package co.mz.policemanager.entity;

import java.io.Serializable;

public class Localidade implements Serializable {

    private Long id;
    private String localidadeCode;
    private String nome;
    private Distrito distrito;

    public Localidade() {
    }

    public Localidade(Long id, String localidadeCode, String nome, Distrito distrito) {
        this.id = id;
        this.localidadeCode = localidadeCode;
        this.nome = nome;
        this.distrito = distrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalidadeCode() {
        return localidadeCode;
    }

    public void setLocalidadeCode(String localidadeCode) {
        this.localidadeCode = localidadeCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return "Localidade{" +
                "id=" + id +
                ", localidadeCode='" + localidadeCode + '\'' +
                ", nome='" + nome + '\'' +
                ", distrito=" + distrito +
                '}';
    }
}
