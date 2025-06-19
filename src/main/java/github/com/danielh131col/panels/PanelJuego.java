package github.com.danielh131col.panels;

import github.com.danielh131col.TiemposGuardados;
import github.com.danielh131col.utils.Configuracion;
import github.com.danielh131col.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PanelJuego extends JPanel implements ActionListener, MouseListener {

    private final Juego juego;
    private Timer timer;
    private int x = 50, y = 50, size = 50;
    private int puntos = 0;
    private int fallos = 0;
    private boolean juegoTerminado = false;
    private boolean gano = false;
    private final Random random = new Random();

    private final JButton botonSalir;
    private long tiempoInicio;
    private long tiempoFinal;

    public PanelJuego(Juego juego) {
        this.juego = juego;

        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.addMouseListener(this);

        // Botón Salir
        botonSalir = new JButton("Salir");
        botonSalir.setBounds(700, 10, 80, 30);
        botonSalir.setFocusPainted(false);
        botonSalir.setBackground(Color.RED);
        botonSalir.setForeground(Color.WHITE);
        botonSalir.addActionListener(e -> {
            timer.stop();
            juego.mostrarMenuPrincipal();
        });
        this.add(botonSalir);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                posicionarBotonSalir();
            }
        });

        posicionarBotonSalir();

        timer = new Timer(300, this);
        timer.setRepeats(false);
        timer.start();
        tiempoInicio = System.currentTimeMillis();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (juegoTerminado) {
            g.setColor(gano ? Color.GREEN : Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String mensaje = gano
                    ? "¡Ganaste! Tu tiempo fue: " + obtenerTiempo()
                    : "GAME OVER";
            FontMetrics fm = g.getFontMetrics();
            int xTexto = (getWidth() - fm.stringWidth(mensaje)) / 2;
            int yTexto = getHeight() / 2;
            g.drawString(mensaje, xTexto, yTexto);
            return;
        }

        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntos: " + puntos, 10, 20);
        g.drawString("Fallos: " + fallos + " / " + Configuracion.fallosMaximos, 10, 45);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado) {
            moverCirculo();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (juegoTerminado) return;

        int mouseX = e.getX();
        int mouseY = e.getY();

        boolean acierto = mouseX >= x && mouseX <= x + size && mouseY >= y && mouseY <= y + size;

        if (acierto) {
            puntos++;
            if (puntos >= 5) {
                tiempoFinal = System.currentTimeMillis();
                juegoTerminado = true;
                gano = true;
                timer.stop();

                String tiempo = obtenerTiempo();
                TiemposGuardados.agregarTiempo(tiempo);
            }
        } else {
            fallos++;
            if (fallos >= Configuracion.fallosMaximos) {
                juegoTerminado = true;
                gano = false;
                timer.stop();
            }
        }

        if (SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isRightMouseButton(e)) {
            moverCirculo();
        }

        repaint();
    }

    private void moverCirculo() {
        x = random.nextInt(getWidth() - size);
        y = random.nextInt(getHeight() - size);
    }

    private String obtenerTiempo() {
        long segundos = (tiempoFinal - tiempoInicio) / 1000;
        long minutos = segundos / 60;
        segundos = segundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    private void posicionarBotonSalir() {
        int margin = 10;
        int anchoBoton = botonSalir.getPreferredSize().width;
        int altoBoton = botonSalir.getPreferredSize().height;
        botonSalir.setBounds(getWidth() - anchoBoton - margin, margin, anchoBoton, altoBoton);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}