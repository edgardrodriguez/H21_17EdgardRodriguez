/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PagoImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.PagoModelo;

/**
 *
 * @author ZERO
 */
@Named(value = "pagoC")
@SessionScoped
public class pagoC implements Serializable {

    private PagoModelo cuot;
    private PagoImpl dao;

    private List<PagoModelo> listadoCuot;
    private List<PagoModelo> listAct;

    public pagoC() {
        cuot = new PagoModelo();
        dao = new PagoImpl();
    }

    public void registrar() throws Exception {
        try {
            System.out.println(cuot);
            dao.registrar(cuot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cuot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(cuot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }

    public void limpiar() {
        cuot = new PagoModelo();
    }

    public void listar() {
        try {
            listadoCuot = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void obtenerCuota() throws Exception {

        try {
            if (cuot.getFKconferenciaP()> 0) {
                cuot.setCantidadPagar(dao.obtenerSaldoCuota(cuot.getFKconferenciaP(), cuot.getFKpersonaP()));

            }
        } catch (Exception e) {
            System.out.println("Error en obtener cuota " + e.getMessage());
        }

    }

    public PagoModelo getCuot() {
        return cuot;
    }

    public void setCuot(PagoModelo cuot) {
        this.cuot = cuot;
    }

    public PagoImpl getDao() {
        return dao;
    }

    public void setDao(PagoImpl dao) {
        this.dao = dao;
    }

    public List<PagoModelo> getListadoCuot() {
        return listadoCuot;
    }

    public void setListadoCuot(List<PagoModelo> listadoCuot) {
        this.listadoCuot = listadoCuot;
    }

    public List<PagoModelo> getListAct() {
        try {
            listAct=dao.listarAct();
        } catch (SQLException ex) {
            Logger.getLogger(pagoC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(pagoC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAct;
    }

    public void setListAct(List<PagoModelo> listAct) {
        this.listAct = listAct;
    }

}
