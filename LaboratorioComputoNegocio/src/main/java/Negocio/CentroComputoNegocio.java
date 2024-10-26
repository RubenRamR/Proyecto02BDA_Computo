/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.CentroComputoDAO;
import DTOs.CentroComputoDTO;
import InterfacesNegocio.ICentroComputoNegocio;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class CentroComputoNegocio implements ICentroComputoNegocio {

    CentroComputoDAO centroComputoDAO;

    public CentroComputoNegocio() {
    }

    public CentroComputoNegocio(CentroComputoDAO centroComputoDAO) {
        this.centroComputoDAO = centroComputoDAO;
    }

    @Override
    public void insertarCentroComputo(CentroComputoDTO centroComputo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editarCentroComputo(CentroComputoDTO centroComputo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCentroComputoPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CentroComputoDTO obtenerCentroComputoPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CentroComputoDTO> obtenerTodosLosCentroComputo() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
