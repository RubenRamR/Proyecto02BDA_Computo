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

    @ManyToOne
    @JoinColumn(name = "idCentroComputo", nullable = false)
    private CentroComputoEntidad centroComputo;

    @OneToMany(mappedBy = "computadora", cascade = CascadeType.PERSIST)
    private List<ReservaEntidad> reservas = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "computadora_software", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "idComputadora"), // FK a ComputadoraEntidad
            inverseJoinColumns = @JoinColumn(name = "idSoftware") // FK a SoftwareEntidad
    )
    private List<SoftwareEntidad> softwareList = new ArrayList<>();

    public ComputadoraEntidad() {
    }

    public ComputadoraEntidad(String nombreAlumno, Estado estado, int numeroMaquina, String direccionIP, TipoCompu tipoCompu, CentroComputoEntidad centroComputo, List<SoftwareEntidad> softwareList) {
        this.nombreAlumno = nombreAlumno;
        this.estado = estado;
        this.numeroMaquina = numeroMaquina;
        this.direccionIP = direccionIP;
        this.tipoCompu = tipoCompu;
        this.centroComputo = centroComputo;
        this.softwareList = softwareList;
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

    public List<SoftwareEntidad> getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(List<SoftwareEntidad> softwareList) {
        this.softwareList = softwareList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ComputadoraEntidad{id=").append(id)
                .append(", nombreAlumno='").append(nombreAlumno).append('\'')
                .append(", estado=").append(estado)
                .append(", numeroMaquina=").append(numeroMaquina)
                .append(", direccionIP='").append(direccionIP).append('\'')
                .append(", tipoCompu=").append(tipoCompu)
                .append(", centroComputo=").append(centroComputo != null ? centroComputo.getId() : "null")
                .append(", reservasSize=").append(reservas != null ? reservas.size() : "null")
                .append(", softwareListSize=").append(softwareList != null ? softwareList.size() : "null")
                .append('}');
        return sb.toString();
    }

}
