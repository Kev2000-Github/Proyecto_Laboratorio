/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DAO.CharlaDao;
import DAO.general.DaoFactory;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Charla;
import modelos.Usuario;
import vistas.swing.VentanaCharlas;


/**
 *
 * @author sarah
 */
public class ControladorCharla extends ControladorGeneral implements ListSelectionListener{

    VentanaCharlas ventana;
    DaoFactory daoFactory;
    
        public ControladorCharla(Usuario user) {
        super(user);
        //Inicializo las vars de arriba
        daoFactory = new DaoFactory();
        ventana = new VentanaCharlas(this, this, this);
        //Seteo la config inicial de la ventana
        ventana.setVisible(true);
        //Ejecuto metodo local que llena datos de la ventana y dao
        initCrear();
    }
    
   public void initCrear() {

        //CONECCION DE DAO - VENTANA
        //PARA ENVIAR COMO PARAM A fillCharlas() String fundacion_id = ((ComboboxItem) windowCrear.getFundacion().getSelectedItem()).getId();
        fillCharlas();

    }     
    //TRAER EL/LOS PARAM/S AL METODO TAMBIEN:
    public void fillCharlas() {
        DefaultTableModel modelCharlas = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        CharlaDao charlaDao = new CharlaDao();
        List<Charla> ch_list = charlaDao.getCharlasByType();
        modelCharlas.setColumnCount(5);
        modelCharlas.setColumnIdentifiers(new Object[]{"Seleccionar", "ID", "Nombre", "Costo"});

        for (Charla c : ch_list) {
            modelCharlas.addRow(new Object[]{Boolean.FALSE, c.getId(), c.getTema(), c.getDireccion(), c.getOrganismo(), c.getFecha()});
        }
        ventana.setModelTablaCharla(modelCharlas);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
