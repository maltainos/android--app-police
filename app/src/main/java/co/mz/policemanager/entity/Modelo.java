package co.mz.policemanager.entity;

import java.io.Serializable;

public class Modelo implements Serializable {

    private Long id;
    private String modeloCode;
    private String descricao;
    private Categoria categoria;

    public Modelo(Long id, String modeloCode, String descricao, Categoria categoria) {
        this.id = id;
        this.modeloCode = modeloCode;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Modelo(String modeloCode, String descricao, Categoria categoria) {
        this.modeloCode = modeloCode;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModeloCode() {
        return modeloCode;
    }

    public void setModeloCode(String modeloCode) {
        this.modeloCode = modeloCode;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ModeloEntity{" +
                "id=" + id +
                ", modeloCode='" + modeloCode + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
