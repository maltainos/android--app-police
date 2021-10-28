package co.mz.policemanager.entity;

import java.io.Serializable;

public class LocalEmissao implements Serializable {

    private Long id;
    private String localEmissaoCode;
    private Localidade localidade;

    public LocalEmissao(Localidade localidade) {
        this.localidade = localidade;
    }

    public LocalEmissao(Long id, String localEmissaoCode, Localidade localidade) {
        this.id = id;
        this.localEmissaoCode = localEmissaoCode;
        this.localidade = localidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalEmissaoCode() {
        return localEmissaoCode;
    }

    public void setLocalEmissaoCode(String localEmissaoCode) {
        this.localEmissaoCode = localEmissaoCode;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    @Override
    public String toString() {
        return "LocalEmissao{" +
                "id=" + id +
                ", localEmissaoCode='" + localEmissaoCode + '\'' +
                ", localidade=" + localidade +
                '}';
    }
}
