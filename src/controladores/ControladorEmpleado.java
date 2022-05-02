package controladores;

import DAO.EmpleadoDao;

import DAO.general.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Usuario;
import javax.swing.JLabel;
import modelos.Empleado;
import vistas.swing.VentanaGestionarBackOffice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorEmpleado extends ControladorGeneral implements ListSelectionListener {

    VentanaGestionarBackOffice window;
    DaoFactory daoFactory;

    public ControladorEmpleado(Usuario user) {
        super(user);
        daoFactory = new DaoFactory();
        window = new VentanaGestionarBackOffice("Gestionar Empleados", this, this);
        window.setVisible(true);
        init();
    }

    public void init() {
        fillEmpleados();

    }

    public void fillEmpleados() {
        DefaultTableModel modelEmpleados = new DefaultTableModel();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        List<Empleado> eList = empleadoDao.getAll();
        modelEmpleados.setColumnCount(4);
        modelEmpleados.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Apellido", "Cedula", "Correo"});
        for (Empleado e : eList) {
            modelEmpleados.addRow(new Object[]{
                e.getId(),
                e.getPersona().getNombre(),
                e.getPersona().getApellido(),
                e.getPersona().getCedula(),
                e.getPersona().getCorreo()});
        }
        window.setModeloTabla(modelEmpleados);
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
                EmpleadoDao empDao = new EmpleadoDao();
                Empleado emp = new Empleado();
                emp.setId(idString);
                empDao.delete(emp);
                init();
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
                window.dispose();
                new ControladorUpdatePersona(user, "empleado", idString);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }

            //  window.dispose();
            //  new ControladorPersona(user, "beneficiario", null);
        }
        if (source == window.getCrear()) {
            window.dispose();
            new ControladorAddPersona(user, "empleado", null);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if (source.equals("javax.swing.JLabel")) {
            JLabel lbl = (JLabel) e.getSource();
            if (lbl.getName() == "goHome") {
                window.setVisible(false);
                new ControladorHome(user);
            }
            if(lbl.getName() == "goBack"){
                window.dispose();
                new ControladorRegistros(user);
            }
        }
    }

}
