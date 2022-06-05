package controladores.Fundacion;

import DAO.FundacionDao;
import DAO.PersonaDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Persona;
import vistas.swing.VentanaCrearFundacion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorAddFundacion extends ControladorGeneral implements ListSelectionListener {

    VentanaCrearFundacion window;
    DaoFactory daoFactory;
    FundacionDao fundacionDao;

    public ControladorAddFundacion(IRouter router) {
        super("addFundacion", router);
        daoFactory = new DaoFactory();
        fundacionDao = new FundacionDao();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaCrearFundacion("Crear Fundacion", this, this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void goBack() {
        router.notify(this, "go-fundacion");
    }

    public void clear() {
        window.getNombre().setTextField("");
        window.getPorcentajeAnual().setTextField("");
        window.getPresupuesto().setTextField("");
    }

    public void save() {
        create();
    }

    public Boolean validateForm() {
        return window.getNombre().getTextField().isEmpty()
                || window.getPresupuesto().getTextField().isEmpty()
                || window.getPorcentajeAnual().getTextField().isEmpty();
    }

    public Fundacion getFundacion(){
        Fundacion fundacion = new Fundacion();
        fundacion.setId(window.getSaltString());
        fundacion.setNombre(window.getNombre().getTextField());
        fundacion.setPorcentajePartidoAnual(Float.parseFloat(window.getPorcentajeAnual().getTextField()));
        fundacion.setPresupuesto(Float.parseFloat(window.getPresupuesto().getTextField()));
        return fundacion;
    }

    public Boolean isCreationValid(Fundacion fundacion){
        boolean hasEmptyFields = validateForm();
        if (hasEmptyFields) {
            window.mostrarMensaje("Faltan campos por llenar");
            return false;
        }
        List<Fundacion> fundaciones = fundacionDao.getAll();
        int porcentajeTotal = 0;
        for(int i = 0; i < fundaciones.size(); i++){
            porcentajeTotal += fundaciones.get(i).getPorcentajePartidoAnual();
        }
        if(porcentajeTotal + fundacion.getPorcentajePartidoAnual() > 100){
            window.mostrarMensaje("el porcentaje total fue excedido, por favor reducir el de otras fundaciones primero");
            return false;
        }
        return true;
    }

    public void create() {
        try {
            Fundacion newFundacion = getFundacion();
            if(isCreationValid(newFundacion)) {
                String entity = "fundacion";
                System.out.println("save" + '-' + entity);
                System.out.println(newFundacion.toString());
                Fundacion fundacionExiste = fundacionDao.get(newFundacion.getId());
                if (fundacionExiste != null) {
                    window.mostrarMensaje("Ya existe un registro de este " + entity);
                    return;
                }
                fundacionDao.save(newFundacion);
                window.mostrarMensaje("Se agrego el registro con exito ");
                clear();
            }

            //((VentanaCrearSolicitud) window).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.save()" + e);
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
