package modelos;

import java.util.ArrayList;

public class Presupuesto {
    ArrayList<PresupuestoItem> items;
    String solicitudId;

    public Presupuesto(){}

    public Presupuesto(ArrayList<PresupuestoItem> items, String solicitudId){
        this.items = items;
        this.solicitudId = solicitudId;
    }

    public ArrayList<PresupuestoItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<PresupuestoItem> items) {
		this.items = items;
	}

    public void addItem(PresupuestoItem item){
        this.items.add(item);
    }

    public void removeItem(PresupuestoItem item){
        this.items.remove(item);
    }

    public void clear(){
        this.items.clear();
    }

    public float calcularCostoTotal(){
        float costo = 0;
        for(PresupuestoItem item : items){
            costo += item.getPrecio();
        }
        return costo;
    }

    public void setSolicitudId(String solicitudId){
        this.solicitudId = solicitudId;
    }

    public String getSolicitudId(){
        return solicitudId;
    }
}
