package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Ordenador {
    @Id
    private int _id;
    private String nombre;
    private List<String> componentes;
    private double precio;

    public Ordenador(int _id, String nombre, List<String> componentes, double precio) {
        this._id = _id;
        this.nombre = nombre;
        this.componentes = componentes;
        this.precio = precio;
    }

    public Ordenador() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<String> componentes) {
        this.componentes = componentes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Ordenador{" +
                "_id=" + _id +
                ", nombre='" + nombre + '\'' +
                ", componentes=" + componentes +
                ", precio=" + precio +
                '}';
    }
}
