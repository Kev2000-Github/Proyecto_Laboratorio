/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Reportes;
import javax.swing.table.DefaultTableModel;
import modelos.Beneficiario;
import modelos.Fundacion;
import modelos.Solicitud;
import vistas.swing.VentanaDetalleSolicitante;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import DAO.BeneficiarioDao;
import DAO.FundacionDao;
import DAO.SolicitudDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;



/**
 *
 * @author prometheus
 */
public class ControladorListaSolicitante extends ControladorGeneral {
    VentanaDetalleSolicitante window;
    SolicitudDao solicitudDao;
    
    public ControladorListaSolicitante(IRouter router) {
        super("listaSolicitante", router);
        solicitudDao = new SolicitudDao();     
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaDetalleSolicitante(this, this);
        window.setVisible(true);
        fillSolicitantes();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillSolicitantes() {
        DefaultTableModel modelSolicitantes = new DefaultTableModel();
        List<Solicitud> solicitudes = solicitudDao.getAllPending();  
        modelSolicitantes.setColumnCount(3);
        modelSolicitantes.setColumnIdentifiers(new String[]{"Solicitud", "Fundacion", "Beneficiario"});

        for (Solicitud s : solicitudes) {
            FundacionDao fundacionDao = new FundacionDao();
            Fundacion fundacion = fundacionDao.get(s.getFundacionId());
            BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
            Beneficiario beneficiario = beneficiarioDao.get(s.getBeneficiarioId());
            modelSolicitantes.addRow(new Object[]{s.getId(), 
                fundacion.getNombre(), 
                beneficiario.getNombre() +" "+ beneficiario.getApellido()});
        }
        window.setModelSolicitantes(modelSolicitantes);
    }

    /*
    public void fillFundacion() {
        DefaultComboBoxModel modelFundacion = new DefaultComboBoxModel();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundacionList = fundacionDao.getAll();
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                    new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundacioncb(modelFundacion);
    } */
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
    
    
}
