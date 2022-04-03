package DAO;

import java.util.Objects;
import java.util.Optional;
import java.util.Vector;

import modelos.Persona;

public class PersonaDao implements IDao<Persona> {

    private Vector<Persona> personas = new Vector();

    @Override
    public Persona get(long id) {
        return personas.get((int) id);
    }

    @Override
    public Vector<Persona> getAll() {
        return personas;
    }

    @Override
    public void save(Persona user) {
        personas.add(user);
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
