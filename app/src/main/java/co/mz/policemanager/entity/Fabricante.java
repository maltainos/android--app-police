package co.mz.policemanager.entity;

import java.io.Serializable;

public class Fabricante implements Serializable {

    private Long id;
    private String fabricanteCode;
    private String nome;

    public Fabricante(String fabricanteCode, String nome) {
        this.fabricanteCode = fabricanteCode;
        this.nome = nome;
    }

    public Fabricante(Long id, String fabricanteCode, String nome) {
        this.id = id;
        this.fabricanteCode = fabricanteCode;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricanteCode() {
        return fabricanteCode;
    }

    public void setFabricanteCode(String fabricanteCode) {
        this.fabricanteCode = fabricanteCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Faricante{" +
                "id=" + id +
                ", fabricanteCode='" + fabricanteCode + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
