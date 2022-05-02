package DAO.general;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.GobernacionDao;
import DAO.PermisoDao;
import DAO.PersonaDao;
import DAO.RolDao;
import DAO.SolicitudDao;
import DAO.UsuarioDao;
import DAO.CharlaDao;

public class DaoFactory {
    public IDao getDao(String entity){
        if(entity.equals("gobernacion")) return new GobernacionDao();
        if(entity.equals("empleado")) return new EmpleadoDao();
        if(entity.equals("persona")) return new PersonaDao();
        if(entity.equals("usuario")) return new UsuarioDao();
        if(entity.equals("permiso")) return new PermisoDao();
        if(entity.equals("rol")) return new RolDao();
        if(entity.equals("solicitud")) return new SolicitudDao();
        if(entity.equals("beneficiario")) return new BeneficiarioDao();
        if(entity.equals("charla")) return new CharlaDao();
        return null;
    }
}
