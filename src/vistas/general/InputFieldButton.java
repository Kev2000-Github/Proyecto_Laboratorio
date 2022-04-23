package vistas.general;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class InputFieldButton extends javax.swing.JPanel{
    private JLabel label;
    private JTextField textField;
    private String labelText = "field";
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton btn;

	public InputFieldButton(String label) {
		super();
        labelText = label;
        initGUI();
	}
	
	private void initGUI() {
        this.setSize(200, 20);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        //this.setBackground(Color.orange);
        
        {
            leftPanel = new JPanel();
            //leftPanel.setBackground(Color.green);
            label = new JLabel();
            label.setText(labelText);
            leftPanel.add(label);
            this.add(leftPanel, BorderLayout.WEST);
        }
        {
            rightPanel = new JPanel();
            //rightPanel.setBackground(Color.blue);
            textField = new JTextField(20);
            rightPanel.add(textField);
            this.add(rightPanel, BorderLayout.EAST);
        }
        {
            btn = new JButton();
            rightPanel.add(btn);
            btn.setText("search");
        }
	}	

    public String getValue(){
        return textField.getText();
    }

    public void clear(){
        textField.setText("");
    }

    public void setEnabled(boolean status){
        textField.setEnabled(status);
    }

    public void agregarListener(ActionListener accion){
        btn.addActionListener(accion);
    }
}
