package vistas.general;

public class ComboboxItem {
    private String i;
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComboboxItem(String i, String description) {
        this.i = i;
        this.description = description;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return description;
    }

    public String getI() {
        return this.i;
    }

    public void setI(String i) {
        this.i = i;
    }

}
