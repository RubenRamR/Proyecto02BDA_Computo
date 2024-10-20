/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM.Estado;
import java.io.Serializable;
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

    @Column(name = "software", length = 60, nullable = false)
    private String software;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCentroComputo", referencedColumnName = "idCentroComputo")
    private CentroComputoEntidad centroComputo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    private EstudianteEntidad estudiante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
