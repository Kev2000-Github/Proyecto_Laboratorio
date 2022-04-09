package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.Vector;

import config.Connection.Conne;
import modelos.Persona;

public class PersonaDao implements IDao<Persona> {

    private Vector<Persona> personas = new Vector();

    @Override
    public Persona get(String cedula) {
		PreparedStatement stmt = null;
        Connection conn = null;
		Persona persona = null;
		try {
			conn = Conne.Conexion();
			stmt = conn.prepareStatement("SELECT * FROM usuario where cedula =\"?\"");
            stmt.setString(1,cedula);
            
			ResultSet rs = stmt.executeQuery();
            persona = new Persona();
            rs.next();
            persona.setCedula(rs.getString("cedula"));	
            persona.setNombre(rs.getString("nombre"));	
            persona.setApellido(rs.getString("apellido"));	
            persona.setDireccion(rs.getString("direccion"));	
            persona.setTelefono(rs.getString("telefono"));	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Conne.cerrarConexion(conn);
            Conne.cerrarStatement(stmt);
		}
		
		return persona;
    }

    @Override
    public List<Persona> getAll() {
		PreparedStatement stmt = null;
        Connection conn = null;
		List<Persona> list = new ArrayList<Persona>();
		try {
			conn = Conne.Conexion();
			stmt = conn.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                Persona persona = new Persona();
                persona.setCedula(rs.getString("cedula"));	
                persona.setNombre(rs.getString("nombre"));	
                persona.setApellido(rs.getString("apellido"));	
                persona.setDireccion(rs.getString("direccion"));	
                persona.setTelefono(rs.getString("telefono"));	

				list.add(persona);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Conne.cerrarConexion(conn);
            Conne.cerrarStatement(stmt);
		}
		
		return list;
    }

    @Override
    public void save(Persona persona) {
		PreparedStatement stmt = null;
        Connection conn = null;
		try {
			conn = Conne.Conexion();
			stmt = conn.prepareStatement("INSERT INTO Persona(cedula, nombre, apellido, direccion, telefono) VALUE(?,?,?,?,?)");
			UUID uuid = UUID.randomUUID();
            stmt.setString(1, uuid.toString());
			stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
			stmt.setString(4, persona.getDireccion());
			stmt.setString(5, persona.getTelefono());
            stmt.executeQuery();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Conne.cerrarConexion(conn);
            Conne.cerrarStatement(stmt);
		}		
    }

    @Override
    public void update(Persona person, String[] params) {
        person.setCedula(Objects.requireNonNull(
                params[0], "Cedula cannot be null"));
        person.setNombre(Objects.requireNonNull(
                params[1], "Nombre cannot be null"));
        person.setApellido(Objects.requireNonNull(
                params[2], "Apellido cannot be null"));
        person.setDireccion(Objects.requireNonNull(
                params[3], "Direccion cannot be null"));
        person.setTelefono(Objects.requireNonNull(
                params[4], "Direccion cannot be null"));

        personas.add(person);
    }

    @Override
    public void delete(Persona person) {
        personas.remove(person);
    }

}
