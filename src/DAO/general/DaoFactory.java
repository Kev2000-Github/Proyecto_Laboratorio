package DAO.general;

import DAO.EmpleadoDao;
import DAO.PermisoDao;
import DAO.PersonaDao;
import DAO.RolDao;
import DAO.UsuarioDao;

public class DaoFactory {
    public IDao getDao(String entity){
        if(entity.equals("empleado")) return new EmpleadoDao();
        if(entity.equals("persona")) return new PersonaDao();
        if(entity.equals("usuario")) return new UsuarioDao();
        if(entity.equals("permiso")) return new PermisoDao();
        if(entity.equals("rol")) return new RolDao();
        return null;
    }
}