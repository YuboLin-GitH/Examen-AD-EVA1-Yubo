package com.yubo.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Reparaciones")
public class Reparaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idreparacion")
    private int idreparacion;


    @Column(name = "fechareparacion")
    private LocalDate fechareparacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private int precio;

    @Column(name = "pagado")
    private  Boolean pagado;

    @ManyToOne
    @JoinColumn(name = "idcoche", referencedColumnName = "idcoche")
    private Coches coches;


    public Reparaciones() {
    }

    public Reparaciones(int idreparacion,  LocalDate fechareparacion, String descripcion, int precio, Boolean pagado, Coches coches) {
        this.idreparacion = idreparacion;
        this.fechareparacion = fechareparacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.pagado = pagado;
        this.coches = coches;
    }

    @Override
    public String toString() {
        return "Reparaciones{" +
                "idreparacion=" + idreparacion +
                ", fechareparacion=" + fechareparacion +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", pagado=" + pagado +
                ", coches=" + coches +
                '}';
    }

    public int getIdreparacion() {
        return idreparacion;
    }

    public void setIdreparacion(int idreparacion) {
        this.idreparacion = idreparacion;
    }

    public LocalDate getFechareparacion() {
        return fechareparacion;
    }

    public void setFechareparacion(LocalDate fechareparacion) {
        this.fechareparacion = fechareparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Coches getCoches() {
        return coches;
    }

    public void setCoches(Coches coches) {
        this.coches = coches;
    }


}
