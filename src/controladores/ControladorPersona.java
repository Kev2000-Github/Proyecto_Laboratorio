package controladores;


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
import controladores.ControladorHome;
import javax.swing.JButton;
import modelos.Beneficiario;
import vistas.swing.VentanaGuardarPersona;
import vistas.swing.VentanaGestionarBackOffice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorPersona extends ControladorGeneral implements ListSelectionListener {

    VentanaGuardarPersona window;
    DaoFactory daoFactory;

    public ControladorPersona(Usuario user) {
        super(user);
        daoFactory = new DaoFactory();
        window = new VentanaGuardarPersona(this,this);
        window.setVisible(true);
        init();
    }

    public void init() {
      

    }


    @Override
    public void valueChanged(ListSelectionEvent l) {
      
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = (JButton) arg0.getSource();
      
        if ("guardar".equals(source.getName())){
            
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
            if(lbl.getName() == "exitLbl"){
                window.dispose();
             //   new ControladorHome(user);
            }
        }
    }

}
