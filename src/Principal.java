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
    ControladorLogin loginController = new ControladorLogin();
  }
}