package co.mz.policemanager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class Multa implements Serializable {

    private Long id;
    private String multaCode;
    private Driver driver;
    private Veiculo veiculo;
    private Trasito trasito;
    private Inflacao inflacao;
    private BigDecimal valorMultado;
    private String descricao;
    private StatusMulta statusMulta;
    private LocalEmissao localEmissao;

    public Multa(Driver driver, Veiculo veiculo, Trasito trasito,
                 Inflacao inflacao, BigDecimal valorMultado,
                 String descricao, StatusMulta statusMulta, LocalEmissao localEmissao) {
        this.driver = driver;
        this.veiculo = veiculo;
        this.trasito = trasito;
        this.inflacao = inflacao;
        this.valorMultado = valorMultado;
        this.descricao = descricao;
        this.statusMulta = statusMulta;
        this.localEmissao = localEmissao;
    }

    public Multa(Long id, String multaCode, Driver driver,
                 Veiculo veiculo, Trasito trasito,
                 Inflacao inflacao, BigDecimal valorMultado,
                 String descricao, StatusMulta statusMulta, LocalEmissao localEmissao) {
        this.id = id;
        this.multaCode = multaCode;
        this.driver = driver;
        this.veiculo = veiculo;
        this.trasito = trasito;
        this.inflacao = inflacao;
        this.valorMultado = valorMultado;
        this.descricao = descricao;
        this.statusMulta = statusMulta;
        this.localEmissao = localEmissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMultaCode() {
        return multaCode;
    }

    public void setMultaCode(String multaCode) {
        this.multaCode = multaCode;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Trasito getTrasito() {
        return trasito;
    }

    public void setTrasito(Trasito trasito) {
        this.trasito = trasito;
    }

    public Inflacao getInflacao() {
        return inflacao;
    }

    public void setInflacao(Inflacao inflacao) {
        this.inflacao = inflacao;
    }

    public BigDecimal getValorMultado() {
        return valorMultado;
    }

    public void setValorMultado(BigDecimal valorMultado) {
        this.valorMultado = valorMultado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusMulta getStatusMulta() {
        return statusMulta;
    }

    public void setStatusMulta(StatusMulta statusMulta) {
        this.statusMulta = statusMulta;
    }

    public LocalEmissao getLocalEmissao() {
        return localEmissao;
    }

    public void setLocalEmissao(LocalEmissao localEmissao) {
        this.localEmissao = localEmissao;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", multaCode='" + multaCode + '\'' +
                ", driver=" + driver +
                ", veiculo=" + veiculo +
                ", trasito=" + trasito +
                ", inflacao=" + inflacao +
                ", valorMultado=" + valorMultado +
                ", descricao='" + descricao + '\'' +
                ", statusMulta=" + statusMulta +
                ", localEmissao=" + localEmissao +
                '}';
    }
}
