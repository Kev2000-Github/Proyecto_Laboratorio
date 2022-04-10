package vistas.general;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class VentanaGeneralLista<T> extends VentanaGeneral {
	private String entityName = "default";
    private JLabel lblTitulo;
	private JList<String> itemList;
    private JButton edit;
    private JButton delete;
    private JButton create;
	private JButton goHome;
	private String selectedItem;
	protected List<T> items;
	protected DefaultListModel<String> listModel;

    public VentanaGeneralLista(ActionListener accion, List<T> items, String entity){
        super();
		this.items = items;
		this.entityName = entity;
		this.listModel = new DefaultListModel<String>();
		fillListModel();
        initGUI();
		this.agregarListener(accion);
    }

	public void updateList(List<T> items){
		this.items = items;
		listModel.removeAllElements();
		fillListModel();
	}

	public void fillListModel(){
		for(int i = 0; i < items.size(); i++){
			String item = "mock item";
			listModel.addElement(item);
		}
	} 
	
	private void initGUI() {
		try {
            mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			{
				{
					lblTitulo = new JLabel();
					mainContainer.add(lblTitulo);

					lblTitulo.setText(entityName);
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
				{
					itemList = new JList<String>(listModel);
					itemList.setVisibleRowCount(20);
					MouseAdapter mouseListener = new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							selectedItem = (String) itemList.getSelectedValue();
							String id = selectedItem.split(" ")[0];
							delete.putClientProperty("itemId", id);
							edit.putClientProperty("itemId", id);
						}
					};
					itemList.addMouseListener(mouseListener);
					mainContainer.add(new JScrollPane(itemList));
				}
				{
					edit = new JButton();
					mainContainer.add(edit);
					edit.setText("edit");
					edit.setName("edit-" + entityName);
					edit.putClientProperty("listId", "-1");
					edit.putClientProperty("itemId", "-1");
				}
				{
					delete = new JButton();
					mainContainer.add(delete);
					delete.setText("delete");
					delete.setName("delete-" + entityName);
					delete.putClientProperty("listId", "-1");
					delete.putClientProperty("itemId", "-1");
				}
				{
					create = new JButton();
					mainContainer.add(create);
					create.setText("registrar");
					create.setName("go-register_" + entityName);
				}
				{
					goHome = new JButton();
					mainContainer.add(goHome);
					goHome.setText("home");
					goHome.setName("go-hom001");
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
		create.addActionListener(accion);
		goHome.addActionListener(accion);
    }
}
