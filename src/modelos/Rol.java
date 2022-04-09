package modelos;

import java.util.List;

public class Rol {
    private String id;
    private String nombre;
    private List<Permiso> permisos;

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public List<Permiso> getPermisos(){return permisos;}
    public void setPermisos(List<Permiso> permisos){this.permisos = permisos;}
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
