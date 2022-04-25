package vistas;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import modelos.Solicitud;
import vistas.general.VentanaGeneral;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class VentanaSolicitudes extends VentanaGeneral {
    private JLabel lblTitulo;
    private JButton goSolicitud;
    private ArrayList<Solicitud> solicitudes;
    private JButton goBackOffice;

    public VentanaSolicitudes(ActionListener accion, ArrayList<Solicitud> solicitudes){
        super();
        this.solicitudes = solicitudes;
        initGUI();
		this.agregarListener(accion);
    }
	
	private void initGUI() {
		try {
            mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			{
				{
					lblTitulo = new JLabel();
					mainContainer.add(lblTitulo);

					lblTitulo.setText("Home");
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
                {
                    List<List<String>> dataList = new ArrayList<List<String>>();
                    String column[]={"Solicitud","Fundacion","Beneficiario","Encargado"};         
                    solicitudes.forEach(solicitud -> {
                        List<String> row = List.of(
                            solicitud.getId(),
                            solicitud.getFundacionId(),
                            solicitud.getBeneficiarioId(),
                            solicitud.getEmpleadoId()
                        );
                        dataList.add(row);
                    });
                    String[][] data = dataList.stream()
                        .map(l -> l.stream().toArray(String[]::new))
                        .toArray(String[][]::new);
                        
                    JTable jt=new JTable(data,column);    
                    jt.setBounds(30,40,200,300);          
                    JScrollPane sp=new JScrollPane(jt);    
                    MouseAdapter mouseListener = new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int selectedRow = jt.getSelectedRow();
                            int selectedColumn = jt.getSelectedColumn();
                            String id = jt.getModel().getValueAt(selectedRow, selectedColumn).toString();
							goSolicitud.putClientProperty("itemId", id);
						}
					};
					jt.addMouseListener(mouseListener);
                    mainContainer.add(sp);  
                }
				{
					goSolicitud = new JButton();
					mainContainer.add(goSolicitud);
                    goSolicitud.putClientProperty("itemId", null);
					goSolicitud.setText("Ver Solicitud");
					goSolicitud.setName("goSolicitud");
				}
                {
					goBackOffice = new JButton();
					mainContainer.add(goBackOffice);
					goBackOffice.setText("Home");
					goBackOffice.setName("go-hom001");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public void agregarListener(ActionListener accion){
        goSolicitud.addActionListener(accion);
        goBackOffice.addActionListener(accion);
    }
}
