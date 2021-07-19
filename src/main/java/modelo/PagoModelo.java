/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author ZERO
 */
public class PagoModelo {
    
    private int idPago;
    private Date fechaPago;
    private int CantidadPagar;
    private int MontoPagar;
    private int FKpersonaP;
    private int FKconferenciaP;
    private String personasP;
    private String conferenciaP;

    //
     private int idConferencia;
    private String nombreConf;
    private String primerConf;
    private String segundoConf;
    private int precioConf;
    private String descripcion;
    private Date fecha;
    private String estado;
    
    
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getCantidadPagar() {
        return CantidadPagar;
    }

    public void setCantidadPagar(int CantidadPagar) {
        this.CantidadPagar = CantidadPagar;
    }

    public int getMontoPagar() {
        return MontoPagar;
    }

    public void setMontoPagar(int MontoPagar) {
        this.MontoPagar = MontoPagar;
    }

    public int getFKpersonaP() {
        return FKpersonaP;
    }

    public void setFKpersonaP(int FKpersonaP) {
        this.FKpersonaP = FKpersonaP;
    }

    public int getFKconferenciaP() {
        return FKconferenciaP;
    }

    public void setFKconferenciaP(int FKconferenciaP) {
        this.FKconferenciaP = FKconferenciaP;
    }

    public String getPersonasP() {
        return personasP;
    }

    public void setPersonasP(String personasP) {
        this.personasP = personasP;
    }

    public String getConferenciaP() {
        return conferenciaP;
    }

    public void setConferenciaP(String conferenciaP) {
        this.conferenciaP = conferenciaP;
    }

    public int getIdConferencia() {
        return idConferencia;
    }

    public void setIdConferencia(int idConferencia) {
        this.idConferencia = idConferencia;
    }

    public String getNombreConf() {
        return nombreConf;
    }

    public void setNombreConf(String nombreConf) {
        this.nombreConf = nombreConf;
    }

    public String getPrimerConf() {
        return primerConf;
    }

    public void setPrimerConf(String primerConf) {
        this.primerConf = primerConf;
    }

    public String getSegundoConf() {
        return segundoConf;
    }

    public void setSegundoConf(String segundoConf) {
        this.segundoConf = segundoConf;
    }

    public int getPrecioConf() {
        return precioConf;
    }

    public void setPrecioConf(int precioConf) {
        this.precioConf = precioConf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
