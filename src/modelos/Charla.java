package modelos;

import java.util.Date;

public class Charla {

  private String id;
  private String tema;
  private String direccion;
  private String organismo;
  private Date fecha;

  public Charla(
    String codigo,
    String tema,
    String direccion,
    String organismo,
    Date fecha
  ) {
    this.codigo = codigo;
    this.tema = tema;
    this.direccion = direccion;
    this.organismo = organismo;
    this.fecha = fecha;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public void setTema(String tema) {
    this.tema = tema;
  }

  public String getTema() {
    return this.tema;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public void setOrganismo(String organismo) {
    this.organismo = organismo;
  }

  public String getOrganismo() {
    return this.organismo;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Date getFecha() {
    return this.fecha;
  }
}
