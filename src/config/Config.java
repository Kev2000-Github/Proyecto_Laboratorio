package config;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import utils.TXTReader;

//clase Singleton para solo tener una instancia de configuracion global
public class Config {
    private static Config config = null;
    private TXTReader reader = new TXTReader();
    private Map<String, String> configData = new HashMap<String, String>();

    private Config(){
        try{
            Path path = FileSystems.getDefault().getPath("src/config/.env").toAbsolutePath();
            Vector<String> configFileContent = reader.readTXT(path.toString());
            for(int i = 0; i < configFileContent.size(); i++){
                String filteredLine = configFileContent.get(i).replaceAll(" ","");
                String[] lineContent = filteredLine.split("=");
                String key = lineContent[0];
                String value = lineContent[1];
                configData.put(key, value);
            }
        }
        catch(Error err){
            System.out.println("Error constructing the configuration");
            err.printStackTrace();      
        }
    }

    static public Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public String get(String key){
        return configData.get(key);
    }

}
