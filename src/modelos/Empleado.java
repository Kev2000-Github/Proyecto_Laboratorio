package modelos;

public class Empleado extends Persona {

    private String id;
    private String fundacionId;

    public String getFundacionId() {
        return fundacionId;
    }

    public void setFundacionId(String fundacionId) {
        this.fundacionId = fundacionId;
    }

    public Empleado() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
