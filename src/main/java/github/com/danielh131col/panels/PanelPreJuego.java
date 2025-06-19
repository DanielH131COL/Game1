package github.com.danielh131col.panels;

import github.com.danielh131col.Juego;
import github.com.danielh131col.TiemposGuardados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelPreJuego extends JPanel {

    private final Juego juego;
    private final DefaultListModel<String> listaModel;
    private final JList<String> listaTiempos;
    private JLabel cuenta; // ⬅️ Campo de clase

    public PanelPreJuego(Juego juego) {
        this.juego = juego;
        this.setLayout(null);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screen);

        JLabel titulo = new JLabel("Mejores Tiempos");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(screen.width / 2 - 150, 50, 300, 40);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        this.add(titulo);

        listaModel = new DefaultListModel<>();
        listaTiempos = new JList<>(listaModel);
        listaTiempos.setBounds(screen.width / 2 - 100, 100, 200, 200);
        listaTiempos.setFont(new Font("Monospaced", Font.PLAIN, 18));
        this.add(listaTiempos);

        JButton botonJugar = new JButton("Jugar");
        botonJugar.setBounds(screen.width / 2 - 90, 350, 180, 50);
        botonJugar.setFont(new Font("Arial", Font.BOLD, 20));
        botonJugar.setBackground(Color.GREEN);
        botonJugar.addActionListener(this::iniciarCuentaRegresiva);
        this.add(botonJugar);

        this.setBackground(Color.DARK_GRAY);
    }

    public void actualizarTiempos() {
        listaModel.clear();
        for (String tiempo : TiemposGuardados.mejoresTiempos) {
            listaModel.addElement(tiempo);
        }
    }

    private void iniciarCuentaRegresiva(ActionEvent e) {
        if (cuenta != null) {
            this.remove(cuenta);
            cuenta = null;
            this.repaint();
        }

        cuenta = new JLabel("", SwingConstants.CENTER);
        cuenta.setFont(new Font("Arial", Font.BOLD, 60));
        cuenta.setForeground(Color.WHITE);
        cuenta.setBounds(0, 450, Toolkit.getDefaultToolkit().getScreenSize().width, 100);
        this.add(cuenta);
        this.repaint();

        new Thread(() -> {
            try {
                String[] fases = {"3", "2", "1", "¡A jugar!"};
                for (String fase : fases) {
                    cuenta.setText(fase);
                    Thread.sleep(1000);
                }

                SwingUtilities.invokeLater(() -> {
                    this.remove(cuenta);
                    cuenta = null;
                    juego.iniciarJuego();
                });

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}