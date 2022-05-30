package controladores.ControladorComponente;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import controladores.Mediator.IRouter;
import modelos.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public abstract class ControladorGeneral implements ActionListener, MouseListener {
    protected String id;
    protected IRouter router;
    protected Usuario user;
    
    public ControladorGeneral(String id, IRouter router){
        this.id = id;
        this.router = router;
    }

    public abstract void initGUI();

    public abstract void closeGUI();

    public abstract void mostrarMensaje(String mensaje);

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
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                router.clearURI();
                router.addRoute("login");
                router.notify(this, "go-home");
            }
            if(lbl.getName() == "goBack"){
                router.popRoute();
                String uri = router.getURI();
                String[] uriSplitted = uri.split("/");
                String lastLocation = uriSplitted[uriSplitted.length - 1];
                router.popRoute();
                router.notify(this, "go-" + lastLocation);
            }
            if(lbl.getName() == "logout"){
                router.clearURI();
                router.notify(this, "go-login");
            }
        }
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
