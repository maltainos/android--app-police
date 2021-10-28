package co.mz.policemanager.entity;

import java.io.Serializable;

public class Distrito implements Serializable {

    private Long id;
    private String distritoCode;
    private String nome;
    private Provincia provincia;

    public Distrito() {
    }

    public Distrito(Long id, String distritoCode, String nome, Provincia provincia) {
        this.id = id;
        this.distritoCode = distritoCode;
        this.nome = nome;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistritoCode() {
        return distritoCode;
    }

    public void setDistritoCode(String distritoCode) {
        this.distritoCode = distritoCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Distrito{" +
                "id=" + id +
                ", distritoCode='" + distritoCode + '\'' +
                ", nome='" + nome + '\'' +
                ", provincia=" + provincia +
                '}';
    }
}
