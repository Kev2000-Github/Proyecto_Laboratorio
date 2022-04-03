package vistas.general;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class InputField extends javax.swing.JPanel{
    private JLabel label;
    private JTextField textField;
    private String labelText = "field";
    private JPanel leftPanel;
    private JPanel rightPanel;

	public InputField(String label) {
		super();
        labelText = label;
        initGUI();
	}
	
	private void initGUI() {
        this.setSize(200, 20);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        //this.setBackground(Color.orange);
        rightPanel = new JPanel();
        
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
	}	

    public String getValue(){
        return textField.getText();
    }
}
