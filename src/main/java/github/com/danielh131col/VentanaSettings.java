package github.com.danielh131col;

import github.com.danielh131col.utils.Configuracion;

import javax.swing.*;
import java.awt.*;

public class VentanaSettings extends JFrame {

    public VentanaSettings() {
        this.setTitle("Configuración del Juego");
        this.setSize(400, 250);
        this.setResizable(false);
        this.setLayout(new GridLayout(3, 2, 10, 10));
        this.setLocationRelativeTo(null);

        JLabel labelVelocidad = new JLabel("Velocidad (ms):", SwingConstants.RIGHT);
        JTextField campoVelocidad = new JTextField(String.valueOf(Configuracion.velocidad));

        JLabel labelFallos = new JLabel("Fallos máximos:", SwingConstants.RIGHT);
        JTextField campoFallos = new JTextField(String.valueOf(Configuracion.fallosMaximos));

        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(e -> {
            try {
                int nuevaVelocidad = Integer.parseInt(campoVelocidad.getText());
                int nuevosFallos = Integer.parseInt(campoFallos.getText());

                if (nuevaVelocidad < 100 || nuevosFallos < 1) {
                    JOptionPane.showMessageDialog(this, "Valores no válidos.");
                    return;
                }

                Configuracion.velocidad = nuevaVelocidad;
                Configuracion.fallosMaximos = nuevosFallos;

                JOptionPane.showMessageDialog(this, "Configuración guardada.");
                this.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debes ingresar números válidos.");
            }
        });

        this.add(labelVelocidad);
        this.add(campoVelocidad);
        this.add(labelFallos);
        this.add(campoFallos);
        this.add(new JLabel());
        this.add(guardar);

        this.setVisible(true);
    }
}