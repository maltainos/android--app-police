package co.mz.policemanager.entity;

import java.io.Serializable;
import java.util.List;

public class Driver implements Serializable {

    private Long id;
    private String driverCode;
    private String nome;
    private String apelido;
    private String documentoId;
    private List<Veiculo> veiculos;
    private String cartaConducao;

    public Driver() {
    }

    public Driver(Long id, String driverCode, String nome,
                  String apelido, String documentoId,
                  String cartaConducao) {
        this.id = id;
        this.driverCode = driverCode;
        this.nome = nome;
        this.apelido = apelido;
        this.documentoId = documentoId;
        this.veiculos = null;
        this.cartaConducao = cartaConducao;
    }

    public Driver(String driverCode, String nome, String apelido,
                  String documentoId, String cartaConducao) {
        this.driverCode = driverCode;
        this.nome = nome;
        this.apelido = apelido;
        this.documentoId = documentoId;
        this.cartaConducao = cartaConducao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public String getCartaConducao() {
        return cartaConducao;
    }

    public void setCartaConducao(String cartaConducao) {
        this.cartaConducao = cartaConducao;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverCode='" + driverCode + '\'' +
                ", nome='" + nome + '\'' +
                ", apelido='" + apelido + '\'' +
                ", documentoId='" + documentoId + '\'' +
                ", veiculos=" + veiculos +
                ", cartaConducao='" + cartaConducao + '\'' +
                '}';
    }
}
