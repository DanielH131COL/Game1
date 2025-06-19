package github.com.danielh131col;

import github.com.danielh131col.panels.PanelInicio;
import github.com.danielh131col.panels.PanelJuego;
import github.com.danielh131col.panels.PanelPreJuego;

import javax.swing.*;
import java.awt.*;

public class Juego extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    private PanelPreJuego panelPreJuego;

    public Juego() {
        this.setTitle("Juego of Aim");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        PanelInicio inicio = new PanelInicio(this);
        panelPreJuego = new PanelPreJuego(this);
        PanelJuego juego = new PanelJuego(this);

        panelPrincipal.add(inicio, "inicio");
        panelPrincipal.add(panelPreJuego, "prejuego");
        panelPrincipal.add(juego, "juego");

        this.add(panelPrincipal);
        this.setVisible(true);
    }

    public void mostrarMenuPrincipal() {
        cardLayout.show(panelPrincipal, "inicio");
    }

    public void mostrarPreJuego() {
        panelPreJuego.actualizarTiempos();
        cardLayout.show(panelPrincipal, "prejuego");
    }

    public void iniciarJuego() {
        panelPrincipal.remove(2);
        panelPrincipal.add(new PanelJuego(this), "juego");
        cardLayout.show(panelPrincipal, "juego");
    }
}