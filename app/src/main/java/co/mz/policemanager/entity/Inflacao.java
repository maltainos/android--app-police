package co.mz.policemanager.entity;

import java.io.Serializable;

public class Inflacao implements Serializable {

    private Long id;
    private String inflacaoCode;
    private TipoInflacao tipoInflacao;
    private String descricao;

    public Inflacao(Long id,TipoInflacao tipoInflacao) {
        this.id = id;
        this.tipoInflacao = tipoInflacao;
    }

    public Inflacao(Long id, String inflacaoCode, TipoInflacao tipoInflacao, String descricao) {
        this.id = id;
        this.inflacaoCode = inflacaoCode;
        this.tipoInflacao = tipoInflacao;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInflacaoCode() {
        return inflacaoCode;
    }

    public void setInflacaoCode(String inflacaoCode) {
        this.inflacaoCode = inflacaoCode;
    }

    public TipoInflacao getTipoInflacao() {
        return tipoInflacao;
    }

    public void setTipoInflacao(TipoInflacao tipoInflacao) {
        this.tipoInflacao = tipoInflacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Inflacao{" +
                "id=" + id +
                ", inflacaoCode='" + inflacaoCode + '\'' +
                ", tipoInflacao=" + tipoInflacao +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
