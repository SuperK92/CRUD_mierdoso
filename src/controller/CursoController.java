/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Curso;


/**
 *
 * @author Besay
 */
public class CursoController {
    public void insert(Curso curso) throws SQLException {
        String query = "";
        Conexion db = new Conexion();
        //Statement stm = db.conectar().createStatement();

        query = "INSERT INTO curso VALUES (NULL,?,?,?);";
        
        PreparedStatement ps = db.conectar().prepareStatement(query);
        ps.setString(1, curso.getNombre());
        ps.setInt(2, curso.getFamilia());
        ps.setString(3, curso.getProfesor());
   

        if (ps.executeUpdate() > 0) {
            System.out.println("El registro se insertó exitosamente.");
        } else {
            System.out.println("No se pudo insertar el registro.");
        }

        ps.close();
        db.conexion.close();
    }

    public void getCursoById(int id) {
        String query = "";
        Conexion db = new Conexion();

        try {
            query = "SELECT * FROM curso WHERE id_curso = ?;";
            PreparedStatement ps = db.conectar().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String nombre = rs.getString("nombre_curso");
                int familia = rs.getInt("id_familia");
                String profesor = rs.getString("id_profesor");

                // Imprimir los resultados.
                System.out.format("%s,%d,%s\n", nombre, familia, profesor);

            }

            ps.close();
            db.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllCursos() {
        String query = "";
        Conexion db = new Conexion();

        try {
            query = "SELECT * FROM curso;";
            Statement stm = db.conectar().createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                
                int id = rs.getInt("id_curso");
                String nombre = rs.getString("nombre_curso");
                int familia = rs.getInt("id_familia");
                String profesor = rs.getString("id_profesor");

                // Imprimir los resultados.
                System.out.format("%d,%s,%d,%s\n", id, nombre, familia, profesor);

            }

            stm.close();
            db.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update_nombre(int id, String str) {
        String query = "";
        Conexion bd = new Conexion();

        try {
            query = "UPDATE curso SET nombre_curso = ? WHERE id_curso = ?;";
            PreparedStatement ps = bd.conectar().prepareStatement(query);
            ps.setString(1, str);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("El registro se actualizó exitosamente.");
            ps.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String query = "";
        Conexion db = new Conexion();

        try {
            query = "DELETE FROM curso WHERE id_curso = ?;";
            PreparedStatement ps = db.conectar().prepareStatement(query);
            ps.setInt(1, id);

            ps.execute();
            System.out.println("El registro se eliminó exitosamente.");
            ps.close();
            db.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
