package controladores.Persona.Beneficiario;

import DAO.BeneficiarioDao;
import DAO.ServicioDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelos.Servicio;
import modelos.Solicitud;
import modelos.Usuario;
import utils.Constants;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaCrearSolicitud;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;
import controladores.Menu.ControladorHome;

import javax.swing.JButton;
import modelos.Beneficiario;
import vistas.swing.VentanaGestionarBackOffice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorBeneficiario extends ControladorGeneral implements ListSelectionListener {

    VentanaGestionarBackOffice window;
    DaoFactory daoFactory;

    public ControladorBeneficiario(Router router) {
        super("beneficiario", router);
        daoFactory = new DaoFactory();
    }

    public void initGUI(){
        window = new VentanaGestionarBackOffice("Gestionar Beneficiarios", this, this);
        window.setVisible(true);
        fillBeneficiarios();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillBeneficiarios() {
        DefaultTableModel modelBeneficiarios = new DefaultTableModel();
        BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
        List<Beneficiario> bList = beneficiarioDao.getAll();
        modelBeneficiarios.setColumnCount(4);
        modelBeneficiarios.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Apellido", "Cedula", "Correo"});
        for (Beneficiario b : bList) {
            modelBeneficiarios.addRow(new Object[]{
                b.getId(),
                b.getNombre(),
                b.getApellido(),
                b.getCedula(),
                b.getCorreo()});
        }
        window.setModeloTabla(modelBeneficiarios);
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {
     
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();
        if (source == window.getEliminar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String idString = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("Cedula: " + idString);
                BeneficiarioDao benDao = new BeneficiarioDao();
                Beneficiario ben = new Beneficiario();
                ben.setId(idString);
                benDao.delete(ben);
                fillBeneficiarios();
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }

        }
        if (source == window.getEditar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String idString = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("Cedula: " + idString);
                router.notify(this, "update-updatebeneficiario-" + idString);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getCrear()) {
            router.notify(this, "go-addbeneficiario");
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
    }

  @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                router.notify(this, "go-home");
            }
            if(lbl.getName() == "goBack"){
                router.notify(this, "go-registros");
            }
        }
    }

}
