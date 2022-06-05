package controladores.Fundacion;

import DAO.BeneficiarioDao;
import DAO.FundacionDao;
import DAO.general.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import modelos.Beneficiario;
import modelos.Fundacion;
import vistas.swing.VentanaGestionarBackOffice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorFundacion extends ControladorGeneral implements ListSelectionListener {

    VentanaGestionarBackOffice window;
    DaoFactory daoFactory;
    FundacionDao fundacionDao;

    public ControladorFundacion(IRouter router) {
        super("fundacion", router);
        daoFactory = new DaoFactory();
        fundacionDao = new FundacionDao();
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaGestionarBackOffice("Gestionar Fundaciones", this, this);
        window.setVisible(true);
        fillFundaciones();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillFundaciones() {
        DefaultTableModel modelFundaciones = new DefaultTableModel();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fList = fundacionDao.getAll();
        modelFundaciones.setColumnCount(4);
        modelFundaciones.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Presupuesto", "Porcentaje anual"});
        for (Fundacion fund : fList) {
            modelFundaciones.addRow(new Object[]{
                fund.getId(),
                fund.getNombre(),
                fund.getPresupuesto(),
                fund.getPorcentajePartidoAnual()});
        }
        window.setModeloTabla(modelFundaciones);
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
                String fundacionId = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("FundacionId: " + fundacionId);
                Fundacion fund = new Fundacion();
                fund.setId(fundacionId);
                fundacionDao.delete(fund);
                fillFundaciones();
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }

        }
        if (source == window.getEditar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String fundacionId = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("FundacionId: " + fundacionId);
                router.notify(this, "update-updateFundacion-" + fundacionId);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getCrear()) {
            router.notify(this, "go-addFundacion");
        }

    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
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
