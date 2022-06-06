package controladores.Persona.Empleado;

import DAO.EmpleadoDao;

import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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

    public ControladorEmpleado(IRouter router) {
        super("empleado", router);
        daoFactory = new DaoFactory();
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaGestionarBackOffice("Gestionar Empleados", this, this);
        window.setVisible(true);
        fillEmpleados();
    }

    public void closeGUI(){
        window.dispose();
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
                e.getNombre(),
                e.getApellido(),
                e.getCedula(),
                e.getCorreo()});
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
                String cedula = window.getTable().getModel().getValueAt(row, 3).toString();
                System.out.println("Cedula: " + idString);
                EmpleadoDao empDao = new EmpleadoDao();
                Empleado emp = new Empleado();
                emp.setCedula(cedula);
                emp.setId(idString);
                empDao.delete(emp);
                fillEmpleados();
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
                router.notify(this, "update-updateEmpleado-" + idString);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getCrear()) {
            router.notify(this, "go-addEmpleado");
        }

    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

}
