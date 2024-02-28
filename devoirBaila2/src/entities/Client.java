package entities;

import java.util.List;

public class Client {
    private int id;
    private String nomComplet;
    private String telephone;
    private String email;
    List<Adresse> adresses;
    public List<Adresse> getAdresses() {
        return adresses;
    }
    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }
    public Client() {
    }
    public int getId() {
        return id;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
