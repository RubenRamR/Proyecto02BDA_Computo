
package DTOs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class UnidadAcademicaDTO {

    private Long id;

    private String nombre;

    private List<CentroComputoDTO> centrosComputo = new ArrayList<>();

    public UnidadAcademicaDTO() {
    }

    public UnidadAcademicaDTO(String nombre) {
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

    public List<CentroComputoDTO> getCentrosComputo() {
        return centrosComputo;
    }

    public void setCentrosComputo(List<CentroComputoDTO> centrosComputo) {
        this.centrosComputo = centrosComputo;
    }

    @Override
    public String toString() {
        return "UnidadAcademicaDTO{" + "id=" + id + ", nombre=" + nombre + ", centrosComputo=" + centrosComputo + '}';
    }

}
