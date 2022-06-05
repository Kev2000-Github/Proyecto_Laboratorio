package controladores.Fundacion;

import DAO.FundacionDao;
import DAO.PersonaDao;
import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorUpdateGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelos.Fundacion;
import modelos.Persona;
import utils.Utils;
import vistas.swing.VentanaEditarFundacion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorUpdateFundacion extends ControladorUpdateGeneral implements ListSelectionListener {

    VentanaEditarFundacion window;
    DaoFactory daoFactory;
    FundacionDao fundacionDao;
    String fundacionId;

    public ControladorUpdateFundacion(IRouter router) {
        super("updateFundacion", router);
        daoFactory = new DaoFactory();
        fundacionDao = new FundacionDao();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void updateId(String id){
        fundacionId = id;
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaEditarFundacion("Editar Fundacion", this, this);
        window.setVisible(true);
        if (this.fundacionId != null) {
                Fundacion model = fundacionDao.get(this.fundacionId);
                fillModel(
                    model.getId(),
                    model.getNombre(),
                    model.getPresupuesto(),
                    model.getPorcentajePartidoAnual()
                );
        }
    }

    public void closeGUI(){
        window.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void clear() {
        window.getFundacionId().setText("");
        window.getNombre().setTextField("");
        window.getPorcentajeAnual().setTextField("");
        window.getPresupuesto().setTextField("");
    }

    public void fillModel(
            String fundacionId,
            String nombre, 
            float presupuesto,
            float porcentajePartidoAnual) {
        window.getNombre().setTextField(nombre);
        window.getFundacionId().setText(fundacionId);
        window.getPresupuesto().setTextField(String.valueOf(presupuesto));
        window.getPorcentajeAnual().setTextField(String.valueOf(porcentajePartidoAnual));
    }

    public void save() {
        update(fundacionId);
    }

    public Boolean validateForm() {
        return window.getFundacionId().getText().isEmpty()
                || window.getNombre().getTextField().isEmpty()
                || window.getPorcentajeAnual().getTextField().isEmpty()
                || window.getPresupuesto().getTextField().isEmpty();
    }

    public void goBack() {
        router.notify(this, "go-fundacion");
    }

    public Fundacion getFundacion(){
        Fundacion fundacion = new Fundacion();
        fundacion.setId(fundacionId);
        fundacion.setNombre(window.getNombre().getTextField());
        fundacion.setPorcentajePartidoAnual(Float.parseFloat(window.getPorcentajeAnual().getTextField()));
        fundacion.setPresupuesto(Float.parseFloat(window.getPresupuesto().getTextField()));
        return fundacion;
    }

    public void update(String fundacionId) {
        try {
            if (validateForm()) {
                window.mostrarMensaje("Faltan campos por llenar");
            } else {
                Fundacion newFundacion = getFundacion();
                String entity = "fundacion";
                System.out.println("save" + '-' + entity);
                System.out.println(newFundacion.toString());
                Fundacion fundacion = fundacionDao.get(newFundacion.getId());
                if (fundacion == null) {
                    window.mostrarMensaje("No existe este " + entity);
                    return;
                }
                fundacionDao.update(newFundacion);
                window.mostrarMensaje("Se actualizo el registro con exito ");
            }
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.update()" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();
        if (source == window.getSave()) {
            save();
            goBack();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

}
