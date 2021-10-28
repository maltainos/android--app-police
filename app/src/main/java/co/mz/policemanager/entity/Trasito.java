package co.mz.policemanager.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

public class Trasito implements Serializable {

    private Long id;
    private String codigo;
    private String email;
    private String lastName;
    private String firstName;
    private String password;
    private OffsetDateTime createOn;
    private  OffsetDateTime updateOn;

    public Trasito() {
    }

    public Trasito(String lastName, String email,
                   String firstName, String codigo) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.codigo = codigo;
    }

    public Trasito(String codigo, String email, String lastName, String firstName, String password) {
        this.codigo = codigo;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OffsetDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(OffsetDateTime createOn) {
        this.createOn = createOn;
    }

    public OffsetDateTime getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(OffsetDateTime updateOn) {
        this.updateOn = updateOn;
    }
}
