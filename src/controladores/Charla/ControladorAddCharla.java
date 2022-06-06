package controladores.Charla;

import DAO.CharlaDao;
import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelos.Charla;
import vistas.swing.VentanaCrearCharla;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorAddCharla extends ControladorGeneral implements ListSelectionListener {

    VentanaCrearCharla window;
    DaoFactory daoFactory;
    CharlaDao charlaDao;
    String empleadoId;

    public ControladorAddCharla(IRouter router) {
        super("addCharla", router);
        daoFactory = new DaoFactory();
        charlaDao = new CharlaDao();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaCrearCharla("Crear Charla", this, this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void goBack() {
        router.notify(this, "go-charla");
    }

    public void clear() {
        window.getLugar().setTextField("");
        window.getTema().setTextField("");
        window.getOrganismo().setTextField("");
    }

    public void save() {
        create();
    }

    public Boolean validateForm() {
        return window.getLugar().getTextField().isEmpty()
                || window.getTema().getTextField().isEmpty()
                || window.getOrganismo().getTextField().isEmpty();
    }

    public Charla getCharla(){
        Charla charla = new Charla();
        charla.setDireccion(window.getLugar().getTextField());
        charla.setFecha(window.getFecha().getDate());
        charla.setId(window.getSaltString());
        charla.setOrganismo(window.getOrganismo().getTextField());
        charla.setTema(window.getTema().getTextField());
        return charla;
    }

    public Boolean isCreationValid(){
        boolean hasEmptyFields = validateForm();
        if (hasEmptyFields) {
            window.mostrarMensaje("Faltan campos por llenar");
            return false;
        }
        return true;
    }

    public void create() {
        try {
            Charla newCharla = getCharla();
            if(isCreationValid()) {
                String entity = "charla";
                System.out.println("save" + '-' + entity);
                System.out.println(newCharla.toString());
                Charla charlaExiste = charlaDao.get(newCharla.getId());
                if (charlaExiste != null) {
                    window.mostrarMensaje("Ya existe un registro de este " + entity);
                    return;
                }
                charlaDao.save(newCharla);
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
