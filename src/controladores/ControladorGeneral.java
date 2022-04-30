package controladores;

import modelos.Usuario;
import vistas.general.MetodosGenerales;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class ControladorGeneral implements ActionListener, MouseListener {
    protected Usuario user;
    protected MetodosGenerales window;

    public ControladorGeneral(Usuario user){
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

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
