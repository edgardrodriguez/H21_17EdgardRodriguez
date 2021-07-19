/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.ConferenciaModel;
import dao.ICRUD;

public class ConferenciaImpl extends dao.Conexion implements ICRUD <ConferenciaModel>{

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd/MM/yyyy").parse(fecha) : null;
    }

    @Override
    public List<ConferenciaModel> listarTodos() throws Exception {
        List<ConferenciaModel> listaActividad;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM CONFERENCIAS";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaActividad = new ArrayList<>();
            ConferenciaModel actividad;
            while (rs.next()) {
                actividad = new ConferenciaModel();
                actividad.setIdConferencia(rs.getInt("IDCONF"));
                actividad.setNombreConf(rs.getString("NOMCONF"));
                actividad.setPrimerConf(rs.getString("NOMPRICONF"));
                actividad.setSegundoConf(rs.getString("NOMSEGCONF"));
                actividad.setPrecioConf(rs.getInt("PRECONF"));
                actividad.setDescripcion(rs.getString("DESCONF"));
                actividad.setFecha(rs.getDate("FECCONF"));
                actividad.setEstado(rs.getString("ESTCONF")); 
                listaActividad.add(actividad);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaActividad;
    }

    @Override
    public void registrar(ConferenciaModel actividad) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO CONFERENCIAS(NOMCONF,NOMPRICONF,NOMSEGCONF,PRECONF,DESCONF,FECCONF,ESTCONF) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
             ps.setString(1, actividad.getNombreConf());
            ps.setString(2, actividad.getPrimerConf());
            ps.setString(3, actividad.getSegundoConf());
            ps.setInt(4, actividad.getPrecioConf());
            ps.setString(5, actividad.getDescripcion());
            ps.setString(6, formatter.format(actividad.getFecha()));
            ps.setString(7, actividad.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(ConferenciaModel actividad) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE CONFERENCIAS SET NOMCONF=?, NOMPRICONF=?, NOMSEGCONF=?, PRECONF=?, DESCONF=?,FECCONF=?,ESTCONF=? WHERE IDCONF =?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, actividad.getNombreConf());
            ps.setString(2, actividad.getPrimerConf());
            ps.setString(3, actividad.getSegundoConf());
            ps.setInt(4, actividad.getPrecioConf());
            ps.setString(5, actividad.getDescripcion());
            ps.setString(6, formatter.format(actividad.getFecha()));
            ps.setString(7, actividad.getEstado());
            ps.setInt(8, actividad.getIdConferencia());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ConferenciaModel actividad) throws Exception {
        this.conectar();
        try {
            String sql = "delete from CONFERENCIAS where IDCONF=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, actividad.getIdConferencia());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
