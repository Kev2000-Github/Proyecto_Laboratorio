import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bean.JTextFieldValidator;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class VentanaPrincipal {
  public static void main(String args[]) throws Exception {
    JFrame frame = new JFrame();
    frame.setSize(450, 300);
    frame.setTitle("Cajas de Textos con Validacion");    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    
    JPanel panel = new JPanel();
    panel.setLayout(null);
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    panel.setPreferredSize(new java.awt.Dimension(495, 270));

    JLabel lbl1 = new JLabel("Cualquier Caracter (n):");
    lbl1.setBounds(10, 50, 190, 20);
    panel.add(lbl1);
    JTextFieldValidator txt1 = new JTextFieldValidator();
    txt1.setBounds(258, 50, 170, 20);
    panel.add(txt1);

    JLabel lbl2 = new JLabel("Cualquier Caracter (4):");
    lbl2.setBounds(10, 100, 190, 20);
    panel.add(lbl2);
    JTextFieldValidator txt2 = new JTextFieldValidator();
    txt2.setMaximaLongitud(4);
    txt2.setBounds(258, 100, 170, 20);
    panel.add(txt2);
    
    JLabel lbl3 = new JLabel("Letras Espacios y Numeros (n):");
    lbl3.setBounds(10, 150, 230, 20);
    panel.add(lbl3);
    JTextFieldValidator txt3 = new JTextFieldValidator(JTextFieldValidator.LETRAS_ESPACIOS_Y_NUMEROS);
    txt3.setBounds(258, 150, 170, 20);
    panel.add(txt3);
    
    JLabel lbl4 = new JLabel("Solo Numeros (10):");
    lbl4.setBounds(10, 200, 230, 20);
    panel.add(lbl4);
    JTextFieldValidator txt4 = new JTextFieldValidator();
    txt4.setMaximaLongitud(10);
    txt4.setTipoCaracteresPermitidos(JTextFieldValidator.SOLO_NUMEROS);
    txt4.setBounds(258, 200, 170, 20);
    panel.add(txt4);
    
    frame.setVisible(true);
  }
}