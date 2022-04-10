package vistas.general;
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

import java.awt.event.ActionListener;
import java.util.List;


public class VentanaGeneralLista<T> extends VentanaGeneral {
    private JLabel lblTitulo;
	private JList<String> itemList;
    private JButton edit;
    private JButton delete;
    private JButton create;
	private JButton goHome;
	private String selectedItem;
	protected List<T> items;
	
    public VentanaGeneralLista(ActionListener accion, List<T> items){
        super();
		this.items = items;
        initGUI();
		this.agregarListener(accion);
    }

	public void updateList(List<T> items){
		this.items = items;
		itemList.updateUI();
	}

	public String[] getItemList(){
		String[] itemList = new String[items.size()];
		for(int i = 0; i < itemList.length; i++){
			String item = "mock item";
			itemList[i] = item;
		}
		return itemList;
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
					String[] itemDetails = getItemList();
					itemList = new JList<String>(itemDetails);
					itemList.setVisibleRowCount(20);
					itemList.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e){
							selectedItem = (String) itemList.getSelectedValue();
							System.out.println(selectedItem); 
						}
					});
					mainContainer.add(new JScrollPane(itemList));
				}
				{
					edit = new JButton();
					mainContainer.add(edit);
					edit.setText("edit");
					edit.setName("edit");
				}
				{
					delete = new JButton();
					mainContainer.add(delete);
					delete.setText("delete");
					delete.setName("delete");
				}
				{
					create = new JButton();
					mainContainer.add(create);
					create.setText("registrar");
					create.setName("go-rus001");
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
