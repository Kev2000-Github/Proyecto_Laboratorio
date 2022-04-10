import java.util.List;

import DAO.EmpleadoDao;
import DAO.PermisoDao;
import DAO.PersonaDao;
import DAO.UsuarioDao;
import config.Config;
import config.Connection.Conne;
import controladores.Controlador;
import controladores.ControladorLogin;
import modelos.Empleado;
import modelos.Permiso;
import modelos.Persona;
import modelos.Usuario;

public class Principal {
  public static void main(String args[]) throws Exception {
    System.out.println("Hola");

    //init global config
    Config config = Config.getConfig();
    System.out.println(config.get("database"));
    EmpleadoDao dao = new EmpleadoDao();
    Empleado obj = dao.get("e81a71bf-413a-4554-bcd7-834f7d4fafd8");
    obj.setNombre("kevin alterado");
    dao.update(obj);
    System.out.println("done!");
  }
}