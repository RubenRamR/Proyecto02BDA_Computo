/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "Regla")
public class ReglaEntidad implements Serializable {

    @Id
    @Column(name = "idRegla", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "decripcion", length = 60)
    private String descripcion;

    @ManyToMany(mappedBy = "reglas", cascade =
    {
        CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<CentroComputoEntidad> centrosComputo = new ArrayList<>();

    public ReglaEntidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CentroComputoEntidad> getCentrosComputo() {
        return centrosComputo;
    }

    public void setCentrosComputo(List<CentroComputoEntidad> centrosComputo) {
        this.centrosComputo = centrosComputo;
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
        if (!(object instanceof ReglaEntidad))
        {
            return false;
        }
        ReglaEntidad other = (ReglaEntidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReglaEntidad{" + "id=" + id + ", centrosComputo=" + centrosComputo + '}';
    }

}
