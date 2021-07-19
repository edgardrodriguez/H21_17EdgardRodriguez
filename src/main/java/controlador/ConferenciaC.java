package controlador;

import dao.ConferenciaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ConferenciaModel;


@Named(value = "actividadControlador")
@SessionScoped
public class ConferenciaC implements Serializable {

private ConferenciaModel act;
private ConferenciaImpl dao;
private List<ConferenciaModel> listadoActividad;

    public ConferenciaC() {
        act = new ConferenciaModel();
        dao = new ConferenciaImpl();
    }
    public void registrar() throws Exception {
        try {
            dao.registrar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }

    public void limpiar() {
        act = new ConferenciaModel();
    }

    public void listar() {
        try {
            listadoActividad = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }
    public ConferenciaModel getAct() {
        return act;
    }

    public void setAct(ConferenciaModel act) {
        this.act = act;
    }

    public ConferenciaImpl getDao() {
        return dao;
    }

    public void setDao(ConferenciaImpl dao) {
        this.dao = dao;
    }

    public List<ConferenciaModel> getListadoActividad() {
        return listadoActividad;
    }

    public void setListadoActividad(List<ConferenciaModel> listadoActividad) {
        this.listadoActividad = listadoActividad;
    }
}
