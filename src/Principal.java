import config.Config;
import controladores.ControladorLogin;

public class Principal {
  public static void main(String args[]) throws Exception {
    System.out.println("Hola");

    //init global config
    Config config = Config.getConfig();
    System.out.println(config.get("database"));
    ControladorLogin controladorLogin = new ControladorLogin();
  }
}