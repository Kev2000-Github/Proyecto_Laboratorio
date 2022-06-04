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
import DAO.BeneficiarioDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.SolicitudDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import javax.swing.DefaultComboBoxModel;
import modelos.Servicio;
import vistas.general.ComboboxItem;



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
        fillFundacion();
        //String fundacionId = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
        //fillDetSolicitante(fundacionId);
    }

    public void closeGUI(){
        window.dispose();
    }

        public void fillFundacion() {
        DefaultComboBoxModel<ComboboxItem> modelFundacion = new DefaultComboBoxModel<ComboboxItem>();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundacionList = fundacionDao.getAll();
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundacion(modelFundacion);
    }

    
    public void fillSolicitantes() {
        DefaultTableModel modelSolicitantes = new DefaultTableModel();
        List<Solicitud> solicitudes = solicitudDao.getAllSolicitud();  
        modelSolicitantes.setColumnCount(9);
        modelSolicitantes.setColumnIdentifiers(new String[]{"Solicitud", "Fundacion", "Cedula","Nombre","Apellido","Direccion","Telefono","Correo","Status"});
        for (Solicitud s : solicitudes) {
            FundacionDao fundacionDao = new FundacionDao();
            Fundacion fundacion = fundacionDao.get(s.getFundacionId());
            BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
            Beneficiario beneficiario = beneficiarioDao.get(s.getBeneficiarioId());
            modelSolicitantes.addRow(new Object[]{ 
                s.getId(),
                fundacion.getNombre(),
                beneficiario.getCedula(),
                beneficiario.getNombre(),
                beneficiario.getApellido(),
                beneficiario.getDireccion(),
                beneficiario.getTelefono(),
                beneficiario.getCorreo(),
                s.getStatus()
            });
        }
        window.setModelSolicitantes(modelSolicitantes);
    }
    
    /*
    public void fillDetSolicitante(String fundacionId){
        DefaultTableModel modelDetSoli = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
        List<Beneficiario> fsList = beneficiarioDao.getAllFromFundacion(fundacionId);
        modelDetSoli.setColumnCount(6);
        modelDetSoli.setColumnIdentifiers(new Object[]{"Cedula", "Nombre", "Apellido", "Direccion","Telefono","Correo"});

        for (Beneficiario b : fsList) {
            modelDetSoli.addRow(new Object[]{b.getCedula(), b.getNombre(), b.getApellido(), b.getDireccion(),b.getTelefono(),b.getCorreo()});
        }
        window.setModelSolicitantes(modelDetSoli);    
    } */

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
    
    
}
