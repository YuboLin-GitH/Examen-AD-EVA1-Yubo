package com.yubo.Model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Coches")
public class Coches {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idcoche")
    private int idcoche;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "km")
    private int km;

    @OneToMany(mappedBy = "coches", cascade = CascadeType.ALL)
    private List<Reparaciones> reparaciones;


    public Coches() {
    }

    public Coches(int idcoche, String matricula, String marca, int km) {
        this.idcoche = idcoche;
        this.matricula = matricula;
        this.marca = marca;
        this.km = km;
    }

    @Override
    public String toString() {
        return "Coches{" +
                "idcoche=" + idcoche +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", km=" + km +
                '}';
    }


    public int getIdcoche() {
        return idcoche;
    }

    public void setIdcoche(int idcoche) {
        this.idcoche = idcoche;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public List<Reparaciones> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(List<Reparaciones> reparaciones) {
        this.reparaciones = reparaciones;
    }
}
