package DAO.general;

import DAO.EmpleadoDao;
import DAO.PermisoDao;
import DAO.PersonaDao;
import DAO.RolDao;
import DAO.UsuarioDao;

public class DaoFactory {
    public IDao getDao(String entity){
        if(entity == "empleado") return new EmpleadoDao();
        if(entity == "persona") return new PersonaDao();
        if(entity == "usuario") return new UsuarioDao();
        if(entity == "permiso") return new PermisoDao();
        if(entity == "rol") return new RolDao();
        return null;
    }
}
