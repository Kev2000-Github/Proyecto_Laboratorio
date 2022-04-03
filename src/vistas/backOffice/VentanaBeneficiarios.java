package vistas.backOffice;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.w3c.dom.events.MouseEvent;

import vistas.general.VentanaGeneral;
import vistas.general.InputField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.MouseAdapter;

public class VentanaBeneficiarios extends VentanaGeneral{
    private JLabel lblTitulo;
	private JList<String> userList;
    private JButton edit;
    private JButton delete;
    private JButton gotoRegisterUser;
	private String selectedUser;

    public VentanaBeneficiarios(){
        super();
        initGUI();
    }
	
	private void initGUI() {
		try {
            mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			{
				{
					lblTitulo = new JLabel();
					mainContainer.add(lblTitulo);

					lblTitulo.setText("Usuarios");
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
				{
					String[] data = {"one", "two", "three", "four"};
					userList = new JList<String>(data);
					userList.setVisibleRowCount(20);
					userList.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e){
							selectedUser = (String) userList.getSelectedValue();
							System.out.println(selectedUser); 
						}
					});
					mainContainer.add(new JScrollPane(userList));
				}
				{
					edit = new JButton();
					mainContainer.add(edit);
					edit.setText("edit");
					edit.setName("editBeneficiario");
				}
				{
					delete = new JButton();
					mainContainer.add(delete);
					delete.setText("delete");
					delete.setName("deleteBeneficiario");
				}
				{
					gotoRegisterUser = new JButton();
					mainContainer.add(gotoRegisterUser);
					gotoRegisterUser.setText("registrar");
					gotoRegisterUser.setName("gotoRegisterPerson");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public void agregarListener(ActionListener accion){
        edit.addActionListener(accion);
		delete.addActionListener(accion);
		gotoRegisterUser.addActionListener(accion);
    }
}
