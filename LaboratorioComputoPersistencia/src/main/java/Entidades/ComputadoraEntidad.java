/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM.Estado;
import ENUM.TipoCompu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "Computadora")
public class ComputadoraEntidad implements Serializable {

    @Id
    @Column(name = "idComputadora", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreAlumno", length = 35, nullable = false)
    private String nombreAlumno;

    @Column(name = "Estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "numeroMaquina", nullable = false)
    private int numeroMaquina;

    @Column(name = "direccionIP", nullable = false, unique = true)
    private String direccionIP;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoCompu")
    private TipoCompu tipoCompu;

    @ElementCollection
    @CollectionTable(name = "Software_List", joinColumns = @JoinColumn(name = "idComputadora"))
    @Column(name = "software")
    private List<String> softwares = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idCentroComputo", nullable = false)
    private CentroComputoEntidad centroComputo;

    @OneToMany(mappedBy = "computadora", cascade = CascadeType.ALL)
    private List<ReservaEntidad> reservas = new ArrayList<>();

    public ComputadoraEntidad() {
    }

    public ComputadoraEntidad(String nombreAlumno, Estado estado, int numeroMaquina, String direccionIP, TipoCompu tipoCompu, CentroComputoEntidad centroComputo) {
        this.nombreAlumno = nombreAlumno;
        this.estado = estado;
        this.numeroMaquina = numeroMaquina;
        this.direccionIP = direccionIP;
        this.tipoCompu = tipoCompu;
        this.centroComputo = centroComputo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getNumeroMaquina() {
        return numeroMaquina;
    }

    public void setNumeroMaquina(int numeroMaquina) {
        this.numeroMaquina = numeroMaquina;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public TipoCompu getTipoCompu() {
        return tipoCompu;
    }

    public void setTipoCompu(TipoCompu tipoCompu) {
        this.tipoCompu = tipoCompu;
    }

    public List<String> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<String> softwares) {
        this.softwares = softwares;
    }

    public CentroComputoEntidad getCentroComputo() {
        return centroComputo;
    }

    public void setCentroComputo(CentroComputoEntidad centroComputo) {
        this.centroComputo = centroComputo;
    }

    public List<ReservaEntidad> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntidad> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "ComputadoraEntidad{" + "id=" + id + ", nombreAlumno=" + nombreAlumno + ", estado=" + estado + ", numeroMaquina=" + numeroMaquina + ", direccionIP=" + direccionIP + ", tipoCompu=" + tipoCompu + ", softwares=" + softwares + ", centroComputo=" + centroComputo + ", reservas=" + reservas + '}';
    }

}
