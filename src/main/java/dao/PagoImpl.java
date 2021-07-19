/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.PagoModelo;

/**
 *
 * @author ZERO
 */
public class PagoImpl extends dao.Conexion implements ICRUD<PagoModelo>{
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd/MM/yyyy").parse(fecha) : null;
    }
    @Override
    public void registrar(PagoModelo obj) throws Exception {
        String sql ="insert into PAGOS (FECPAG,CANPAG,MONPAG,IDPER,IDCONF) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, formatter.format(obj.getFechaPago()));
            ps.setInt(2, obj.getCantidadPagar());
            ps.setInt(3, obj.getMontoPagar());
            ps.setInt(4, obj.getFKpersonaP());
            ps.setInt(5, obj.getFKconferenciaP());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Cuota Dao " + e.getMessage());
        }finally{
            this.Cerrar();
        }
    }

    @Override
    public void modificar(PagoModelo obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(PagoModelo obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PagoModelo> listarTodos() throws Exception {
        List<PagoModelo> listado = null;
        PagoModelo cuot;
        String sql = "select * from V_PAGOS";
        ResultSet rs;
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery(); 
            while (rs.next()) {
             cuot = new PagoModelo();
             
             cuot.setIdPago(rs.getInt("IDPAG"));
             cuot.setConferenciaP(rs.getString("PERPAG"));
             cuot.setPersonasP(rs.getString("NOMCONF"));
             cuot.setCantidadPagar(rs.getInt("CANPAG"));
             cuot.setMontoPagar(rs.getInt("MONPAG"));
             cuot.setFechaPago(rs.getDate("FECPAG"));
             cuot.setFKconferenciaP(rs.getInt("IDCONF"));
             cuot.setFKpersonaP(rs.getInt("IDPER"));
             listado.add(cuot);
            }
            rs.close();
            ps.close();
    } catch (Exception e) {
             System.out.println("Error en listarCuota Dao" + e.getMessage());
        }
         return listado;
    
    }
    public int obtenerSaldoCuota(int idCuota, int idPersona) throws SQLException {
        String sql = "SELECT dbo.SaldoCuota(?,?) as saldoCuota";
        ResultSet rs;
        int cuota = -1;
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, idCuota);
            ps.setInt(2, idPersona);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota = rs.getInt("saldoCuota");
            }
        } catch (Exception e) {
            System.out.println("error en cuota Act "+ e.getMessage());
        }
        return cuota;
    }
     public List<PagoModelo> listarAct() throws Exception {
         List<PagoModelo> listAc = null;
        PagoModelo act;
        ResultSet rs;
        String sql = "select * from CONFERENCIAS";
        try {
            listAc = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery(); 
            while (rs.next()) {
                act = new PagoModelo();
                act.setIdConferencia(rs.getInt("IDCONF"));
                act.setNombreConf(rs.getString("NOMCONF"));
                act.setMontoPagar(rs.getInt("PRECONF"));
                act.setFecha(rs.getDate("FECCONF"));
                act.setEstado(rs.getString("ESTCONF"));
                listAc.add(act);
            }
            rs.close();
            ps.close();
            } catch (Exception e) {
            System.out.println("Error en listarCuota Dao" + e.getMessage());
        }
        return listAc;
     }
}
