package github.com.danielh131col.panels;

import github.com.danielh131col.Juego;
import github.com.danielh131col.VentanaSettings;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelInicio extends JPanel {

    public PanelInicio(Juego juego) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.setLayout(null);

        try {
            URL url = new URL("https://i.gifer.com/4xqN.gif");
            ImageIcon originalIcon = new ImageIcon(url);

            Image image = originalIcon.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(image);
            JLabel fondoGif = new JLabel(scaledIcon);
            fondoGif.setBounds(0, 0, screenSize.width, screenSize.height);
            fondoGif.setLayout(null);
            this.add(fondoGif);

            // Título
            JLabel titulo = new JLabel("Bienvenido al Juego");
            titulo.setBounds(screenSize.width / 2 - 200, 50, 400, 50);
            titulo.setFont(new Font("Arial", Font.BOLD, 32));
            titulo.setForeground(Color.WHITE);
            fondoGif.add(titulo);

            // Botón "Play Now"
            JButton botonJugar = new JButton("Play Now");
            botonJugar.setFont(new Font("Arial", Font.BOLD, 24));
            botonJugar.setBounds(screenSize.width / 2 - 90, screenSize.height / 2, 180, 50);
            botonJugar.setFocusPainted(false);
            botonJugar.setBackground(Color.RED);
            botonJugar.addActionListener(e -> juego.mostrarPreJuego());
            fondoGif.add(botonJugar);

            // Botón "Settings"
            JButton botonSettings = new JButton("Settings");
            botonSettings.setFont(new Font("Arial", Font.BOLD, 18));
            botonSettings.setBounds(screenSize.width / 2 - 90, screenSize.height / 2 + 70, 180, 40);
            botonSettings.setFocusPainted(false);
            botonSettings.setBackground(Color.LIGHT_GRAY);
            botonSettings.addActionListener(e -> new VentanaSettings());
            fondoGif.add(botonSettings);

            // Botón "Cerrar El Juego"
            JButton botonSalirGame = new JButton("Salir del Juego");
            botonSalirGame.setFont(new Font("Arial", Font.BOLD, 17));
            botonSalirGame.setBounds(screenSize.width / 2 - 90, screenSize.height / 2 + 120, 180, 40); // ⬅ Y cambiado
            botonSalirGame.setFocusPainted(false);
            botonSalirGame.setBackground(Color.RED);
            botonSalirGame.addActionListener(e -> System.exit(0));
            fondoGif.add(botonSalirGame);

        } catch (Exception e) {
            e.printStackTrace();
            JLabel error = new JLabel("No se pudo cargar el fondo animado");
            error.setBounds(10, 10, 300, 30);
            error.setForeground(Color.RED);
            this.add(error);
        }
    }
}