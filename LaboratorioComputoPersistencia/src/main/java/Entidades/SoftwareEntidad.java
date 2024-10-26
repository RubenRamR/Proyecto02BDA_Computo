/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "Software")
public class SoftwareEntidad implements Serializable {

    @Id
    @Column(name = "idSoftware", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre", length = 60)
    private String nombre;

    @ManyToMany(mappedBy = "softwareList")
    private List<ComputadoraEntidad> computadoras = new ArrayList<>();

    public SoftwareEntidad() {
    }

    public SoftwareEntidad(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ComputadoraEntidad> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(List<ComputadoraEntidad> computadoras) {
        this.computadoras = computadoras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoftwareEntidad))
        {
            return false;
        }
        SoftwareEntidad other = (SoftwareEntidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Software [id=").append(id)
                .append(", nombre=").append(nombre)
                .append("]");
        return sb.toString();
    }

}
