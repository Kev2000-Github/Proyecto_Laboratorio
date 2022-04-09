package DAO;

import java.util.Objects;
import java.util.Vector;

import modelos.Usuario;

//MOCK IMPLEMENTATION, se necesita conexion a bd para obtener la data real
public class PersonaDao implements IDao<Usuario> {
    
    private Vector<Usuario> usuarios = new Vector();
    
    public PersonaDao() {}
    
    @Override
    public Usuario get(String id) {
        return usuarios.get((int) id);
    }
    
    @Override
    public Vector<Usuario> getAll() {
        return usuarios;
    }
    
    @Override
    public void save(Usuario user) {
        usuarios.add(user);
    }
    
    @Override
    public void update(Usuario user, String[] params) {
        user.setUsername(Objects.requireNonNull(
          params[0], "Name cannot be null"));
        user.setPassword(Objects.requireNonNull(
          params[1], "Email cannot be null"));
        
        usuarios.add(user);
    }
    
    @Override
    public void delete(Usuario user) {
        usuarios.remove(user);
    }
}