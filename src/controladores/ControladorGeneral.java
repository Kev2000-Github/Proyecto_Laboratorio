package controladores;

import modelos.Usuario;
import vistas.general.MetodosGenerales;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import vistas.general.ComboboxItem;


public class ControladorGeneral implements ActionListener, MouseListener {
    protected Usuario user;
    protected MetodosGenerales window;

    public ControladorGeneral(Usuario user){
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

     public int findCombobox(JComboBox<ComboboxItem> c, String s) {
        int size = c.getItemCount();
        int itemFind = -1;
        for (int i = 0; i < size; i++) {
            ComboboxItem item = c.getItemAt(i);
            if (s.equals(item.getId())){
                itemFind = i;
            }
            System.out.println("Item at " + item.getId());
        }
        return itemFind;
    }
     
    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                window.dispose();
                new ControladorHome(user);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
