package com.induscore;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Version simplifiée et robuste du Monitoring pour INDUSCORE.
 * Une seule classe pour tout gérer proprement.
 */
public class MachineMonitoringGUI {
    private static final double TEMP_THRESHOLD = 90.0;
    private static final double VIBRATION_THRESHOLD = 7.5;
    private static final int HISTORY_SIZE = 30;

    private JFrame frame;
    private JLabel temperatureLabel;
    private JLabel vibrationLabel;
    private JLabel statusLabel;
    private HistoryChartPanel chartPanel;
    private Timer timer;
    private List<Double> temperatureHistory = new ArrayList<>();
    private List<Double> vibrationHistory = new ArrayList<>();

    // Utilisation de la classe Machine existante d'INDUSCORE
    private Machine machine;

    public static void main(String[] args) {
        // Remplace "CNC Lathe" par le nom de ta machine
        Machine machine = new Machine("CNC Lathe"); 
        new MachineMonitoringGUI(machine).start();
    }

    public MachineMonitoringGUI(Machine machine) {
        this.machine = machine;
    }

    public void start() {
        createAndShowGUI();
        startMonitoring();
    }

    private void createAndShowGUI() {
        frame = new JFrame("INDUSCORE Monitoring - " + machine.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 500);

        // --- Panel Supérieur (Valeurs Temps Réel) ---
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.setBackground(new Color(45, 45, 45)); // Dark mode pro
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        temperatureLabel = createStyledLabel("Temp: --°C", Color.WHITE);
        vibrationLabel = createStyledLabel("Vibr: -- mm/s²", Color.WHITE);
        statusLabel = createStyledLabel("Status: OK", Color.GREEN);

        topPanel.add(temperatureLabel);
        topPanel.add(vibrationLabel);
        topPanel.add(statusLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        // --- Panel Central (Graphique) ---
        chartPanel = new HistoryChartPanel();
        frame.add(chartPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null); // Centrer la fenêtre
        frame.setVisible(true);
    }

    private JLabel createStyledLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(color);
        return label;
    }

    private void startMonitoring() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Mise à jour des données via la classe Machine
                machine.updateData();
                double temp = machine.getTemperature();
                double vibr = machine.getVibrationLevel();

                // Gestion de l'historique
                updateHistory(temp, vibr);

                // Mise à jour de l'interface (Thread Safe)
                SwingUtilities.invokeLater(() -> {
                    temperatureLabel.setText(String.format("Temp: %.1f°C", temp));
                    vibrationLabel.setText(String.format("Vibr: %.2f mm/s²", vibr));
                    updateStatus(temp, vibr);
                    chartPanel.setData(temperatureHistory, vibrationHistory);
                    chartPanel.repaint();
                });
            }
        }, 0, 1000); // Mise à jour chaque seconde
    }

    private void updateHistory(double temp, double vibr) {
        temperatureHistory.add(temp);
        vibrationHistory.add(vibr);
        if (temperatureHistory.size() > HISTORY_SIZE) {
            temperatureHistory.remove(0);
            vibrationHistory.remove(0);
        }
    }

    private void updateStatus(double temp, double vibr) {
        if (temp > TEMP_THRESHOLD || vibr > VIBRATION_THRESHOLD) {
            statusLabel.setText("STATUS: ALERT");
            statusLabel.setForeground(Color.RED);
        } else {
            statusLabel.setText("STATUS: NORMAL");
            statusLabel.setForeground(Color.GREEN);
        }
    }

    /**
     * Classe interne pour le dessin du graphique
     */
    private class HistoryChartPanel extends JPanel {
        private List<Double> tempData = new ArrayList<>();
        private List<Double> vibrData = new ArrayList<>();

        public void setData(List<Double> t, List<Double> v) {
            this.tempData = new ArrayList<>(t);
            this.vibrData = new ArrayList<>(v);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int pad = 40;

            // Fond
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, w, h);

            // Axes
            g2.setColor(Color.BLACK);
            g2.drawLine(pad, h - pad, w - pad, h - pad); // X
            g2.drawLine(pad, pad, pad, h - pad);         // Y

            if (tempData.size() < 2) return;

            // Dessin des lignes
            drawDataLine(g2, tempData, Color.RED, 100.0, pad, w, h);
            drawDataLine(g2, vibrData, Color.BLUE, 15.0, pad, w, h);
            
            // Légende
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.setColor(Color.RED); g2.drawString("Température", pad + 10, pad);
            g2.setColor(Color.BLUE); g2.drawString("Vibration", pad + 110, pad);
        }

        private void drawDataLine(Graphics2D g2, List<Double> data, Color color, double maxVal, int pad, int w, int h) {
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2f));
            double xStep = (double) (w - 2 * pad) / (HISTORY_SIZE - 1);
            double yScale = (double) (h - 2 * pad) / maxVal;

            for (int i = 0; i < data.size() - 1; i++) {
                int x1 = (int) (pad + i * xStep);
                int y1 = (int) (h - pad - data.get(i) * yScale);
                int x2 = (int) (pad + (i + 1) * xStep);
                int y2 = (int) (h - pad - data.get(i + 1) * yScale);
                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }
}