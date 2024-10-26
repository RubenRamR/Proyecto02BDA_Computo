/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import ENUM_P.Estado;
import ENUM_P.TipoCompu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class ComputadoraDTO {

    private Long id;

    private String nombreAlumno;

    private Estado estado;

    private int numeroMaquina;

    private String direccionIP;

    private TipoCompu tipoCompu;

    private CentroComputoDTO centroComputo;

    private List<ReservaDTO> reservas = new ArrayList<>();

    private List<SoftwareDTO> softwareList = new ArrayList<>();

    public ComputadoraDTO() {
    }

    public ComputadoraDTO(String nombreAlumno, Estado estado, int numeroMaquina, String direccionIP, TipoCompu tipoCompu, CentroComputoDTO centroComputo) {
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

    public CentroComputoDTO getCentroComputo() {
        return centroComputo;
    }

    public void setCentroComputo(CentroComputoDTO centroComputo) {
        this.centroComputo = centroComputo;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public List<SoftwareDTO> getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(List<SoftwareDTO> softwareList) {
        this.softwareList = softwareList;
    }

    @Override
    public String toString() {
        return "ComputadoraDTO{" + "id=" + id + ", nombreAlumno=" + nombreAlumno + ", estado=" + estado + ", numeroMaquina=" + numeroMaquina + ", direccionIP=" + direccionIP + ", tipoCompu=" + tipoCompu + ", centroComputo=" + centroComputo + ", reservas=" + reservas + ", softwareList=" + softwareList + '}';
    }

}
