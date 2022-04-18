package config;

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
            Vector<String> configFileContent = reader.readTXT("src/config/.env");
            for(int i = 0; i < configFileContent.size(); i++){
                String filteredLine = configFileContent.get(i).replaceAll(" ","");
                String[] lineContent = filteredLine.split("=");
                String key = lineContent[0];
                String value = lineContent.length > 1 ? lineContent[1] : null;
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
