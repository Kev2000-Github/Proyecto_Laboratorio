package vistas.general;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VentanaEditGeneral<T> extends MetodosGenerales{
    private JLabel lblTitulo;
    protected Map<String, InputField> inputs;
    protected Map<String, InputField> inmutableInputs;
    protected InputField primaryKeyInput;
    private JButton sendBtn;
    private JButton goHome;
    protected T item;
    private String entity;

    public VentanaEditGeneral(
            ActionListener accion, 
            T item, 
            String entity, 
            ArrayList<String> inmutableFields, 
            ArrayList<String> mutableFields
            ){
        super();
        this.entity = entity;
        this.item = item;
        this.inputs = new HashMap<String,InputField>();
        this.inmutableInputs = new HashMap<String,InputField>();
        initGUI(inmutableFields, mutableFields);
        this.agregarListener(accion);
        setItemFields(item);
        setEnableFields(true);
    }

	private void initGUI(ArrayList<String> inmutableFields, ArrayList<String> mutableFields) {
		try {
            mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			{
				{
					lblTitulo = new JLabel();
					mainContainer.add(lblTitulo);

					lblTitulo.setText("Editar " + entity);
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
                for (String field : inmutableFields) {
                    InputField input = new InputField(field + ":");
                    input.setPreferredSize(new Dimension(100,10));
                    input.setEnabled(false);
                    mainContainer.add(input);
                    inmutableInputs.put(field, input);
                }
                for (String field : mutableFields) {
                    InputField input = new InputField(field + ":");
                    input.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(input);
                    inputs.put(field, input);
                }				
                {
					sendBtn = new JButton();
					mainContainer.add(sendBtn);
					sendBtn.setText("Editar");
                    String btnName = String.join("-", "edit",entity);
                    sendBtn.setName(btnName);
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

    public void clear(){
        inputs.forEach((key, input) -> {
            input.clear();
        });
    }

    public void setEnableFields(boolean status){
        inputs.forEach((key, input) -> {
            input.setEnabled(status);
        });
    }

    public void setItemFields(T item){
        this.item = item;
        //mock, ovewrite on child class
        inmutableInputs.forEach((key, input) -> {
            input.setValue("mock");
        });
        inputs.forEach((key, input) -> {
            input.setValue("mock");
        });
        setEnableFields(true);
    }

    public T getItem() {
        //mock, ovewrite on child class
        inputs.forEach((key, input) -> {
            //setValues here
        });
        return item;
    }

    public void agregarListener(ActionListener accion){
        sendBtn.addActionListener(accion);
        goHome.addActionListener(accion);
    }
}
