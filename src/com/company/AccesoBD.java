package com.company;

import java.sql.*;
import java.util.ArrayList;

public class AccesoBD {
    private Connection connection;

    public AccesoBD() {
        String url = "jdbc:mysql:localhost/concesionario";
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            System.out.println("Error de conexión con la base de datos: "+e.getMessage());
        }
    }
    public boolean insertarCoche(Coche coche){
        String consulta = "insert into coches values (null, ?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(consulta);
            ps.setString(1,coche.getMatricula());
            ps.setString(2,coche.getMarca());
            ps.setString(3,coche.getModelo());
            ps.setInt(4,coche.getPotencia());
            ps.setFloat(5,coche.getImporte());

            int rowsaffected = ps.executeUpdate();

            System.out.println("Filas afectadas: "+rowsaffected);
            return rowsaffected > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar datos en la base de datos: "+e.getMessage());
            return false;
        }
    }
    public boolean actualizarCoche(int id){
        PreparedStatement ps;
        try{
            ps = connection.prepareStatement("update coche set marca = ?, matricula = ? where id = ?");
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setInt(3, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch (Exception e){
            System.out.println("Error al borrar los datos: "+e.getMessage());
            return false;
        }
    }

    public boolean borrarCoche(Coche coche){
        PreparedStatement ps;
        try{
            ps = connection.prepareStatement("delete from coches where marca = ? and ? and ? and ? and ?");
            ps.setString(1, coche.getMatricula());
            ps.setString(2, coche.getMarca());
            ps.setString(3, coche.getModelo());
            ps.setInt(4, coche.getPotencia());
            ps.setFloat(5, coche.getImporte());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch (Exception e){
            System.out.println("Error al borrar los datos: "+e.getMessage());
            return false;
        }
    }
    public boolean borrarCochesMarca(String marca){
        PreparedStatement ps;
        try{
            ps = connection.prepareStatement("delete from coches where marca = ?");
            ps.setString(1, marca);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch (Exception e){
            System.out.println("Error al borrar los datos: "+e.getMessage());
            return false;
        }
    }

    public Coche consultarCocheMatricula(String matricula){
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = connection.prepareStatement("select * where matricula = ?");
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            return new Coche(rs.getInt("id"), rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("potencia"), rs.getFloat("importe"));
        }catch (Exception e){
            System.out.println("Error al consultar la BD: "+e.getMessage());
            return new Coche();
        }
    }
    public ArrayList<Coche> visualizarCochesMarca(String marca){
        ArrayList<Coche> coches = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = connection.prepareStatement("select * from coches where marca = ?");
            ps.setString(1,marca);
            rs = ps.executeQuery();
            while (rs.next()){
                coches.add(new Coche(rs.getInt("id"), rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("potencia"), rs.getFloat("importe")));
            }
        }catch (Exception e){
            System.out.println("Error en la consulta: "+e.getMessage());
        }
        return coches;
    }
}

