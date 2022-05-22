package controladores.ControladorComponente;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import controladores.Mediator.Router;
import modelos.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public abstract class ControladorGeneral implements ActionListener, MouseListener {
    protected String id;
    protected Router router;
    protected Usuario user;
    
    public ControladorGeneral(String id, Router router){
        this.id = id;
        this.router = router;
    }

    public abstract void initGUI();

    public abstract void closeGUI();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

}
