package modelos;

public class Usuario {
    private String id;
    private String username;
    private String password;
    private Rol rol;
    private Empleado empleado;

    public Usuario(){}

    public Usuario(String id, String username, String password, Rol rol, Empleado emp){
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.empleado = emp;
    }

    public String setId(){return id;}
    public void getId(String id){this.id = id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public Rol getRol(){return rol;}
    public void setRol(Rol rol){this.rol = rol;}

    public Empleado getEmpleado(){return empleado;}
    public void setEmpleado(Empleado empleado){this.empleado = empleado;}
}
