import java.util.List;

import DAO.UsuarioDao;
import config.Config;
import config.Connection.Conne;
import controladores.Controlador;
import controladores.ControladorLogin;
import modelos.Usuario;

public class Principal {
  public static void main(String args[]) throws Exception {
    System.out.println("Hola");

    //init global config
    Config config = Config.getConfig();
    System.out.println(config.get("database"));
    UsuarioDao usuarioDao = new UsuarioDao();
    List<Usuario> users = usuarioDao.getAll();
    System.out.println("obtained data");
    ControladorLogin controladorLogin = new ControladorLogin();
  }
}