package modelos;

import java.util.List;

public class Rol {
    private String id;
    private List<Permiso> permisos;

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public List<Permiso> getPermisos(){return permisos;}
    public Permiso getPermiso(String id){
        for(int i = 0; i < permisos.size(); i++){
            Permiso currentPerm = permisos.get(i);
            if(currentPerm.getId() == id){
                return currentPerm;
            }
        }
        return null;
    }
    public boolean removePermiso(String id){
        for(int i = 0; i < permisos.size(); i++){
            Permiso currentPerm = permisos.get(i);
            if(currentPerm.getId() == id){
                permisos.remove(i);
                return true;
            }
        }
        return false;
    }
    public void addPermiso(Permiso permiso){
        permisos.add(permiso);
    }
}
