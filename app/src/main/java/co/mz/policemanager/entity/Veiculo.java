package co.mz.policemanager.entity;

import java.io.Serializable;

public class Veiculo implements Serializable {

    private Long id;
    private String carId;
    private String marca;
    private String placa;
    private String corCarro;
    private Modelo modelo;
    private Fabricante fabricante;

    public Veiculo() {
    }

    public Veiculo(Long id, String carId, String marca,
                   String placa) {
        this.id = id;
        this.carId = carId;
        this.marca = marca;
        this.placa = placa;
    }

    public Veiculo(String carId, String marca, String placa, String corCarro) {
        this.carId = carId;
        this.marca = marca;
        this.placa = placa;
        this.corCarro = corCarro;
    }

    public Veiculo(Long id, String carId, String marca, String placa,
                   String corCarro, Modelo modelo, Fabricante fabricante) {
        this.id = id;
        this.carId = carId;
        this.marca = marca;
        this.placa = placa;
        this.corCarro = corCarro;
        this.modelo = modelo;
        this.fabricante = fabricante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", carId='" + carId + '\'' +
                ", marca='" + marca + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }
}
