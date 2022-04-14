package GUI;


import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import static Actions.InputAndUpdate.updateStatus;

public class MainFrame extends JFrame {
    static ArrayList<Cell> cellList = new ArrayList<>();
    static Map map = new Map();
    static int frameWidth=1354,frameHeight=795;
    static int outPanelWidth=300,outPanelHeight=750;
    static int mapWidth=750,mapHeight=750;
    static OutPanel blackOutPanel=new OutPanel(),whiteOutPanel= new OutPanel();
    MainFrame() throws IOException {
        super("Game of Generals");

        Collections.reverse(MainFrame.whiteOutPanel.outButtons);

        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(1368, 795));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.add(map);
        this.add(blackOutPanel);
        this.add(whiteOutPanel, BorderLayout.EAST);
        map.setBounds(outPanelWidth, 0, mapWidth, mapHeight);
        blackOutPanel.setBounds(0, 0, outPanelWidth, outPanelHeight);
        whiteOutPanel.setBounds(outPanelWidth + mapWidth, 0, outPanelWidth, outPanelHeight);
        Update();
    }

    public  void Update() throws IOException {
        updateStatus();
        Cell.updateCells();
        OutPanel.updateOutButtons();
        revalidate();
        repaint();
    }

    void showWinner(String endGame) throws IOException {

        playSound("src/Images/win.wav");
        map.setOpaque(false);
        blackOutPanel.setOpaque(false);
        whiteOutPanel.setOpaque(false);
        this.remove(map);
        this.remove(blackOutPanel);
        this.remove(whiteOutPanel);
        JButton button= new JButton();
        JButton button1 =new JButton(endGame);
        add(button);
        SwingUtilities.invokeLater(() -> {
        JFrame newFrame =new JFrame("Good Job");

        newFrame.setVisible(true);
        newFrame.setResizable(false);
        newFrame.setSize(new Dimension(400, 100));
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newFrame.add(button1);
        });
        button.setFocusable(false);
        ImageIcon icon=new ImageIcon("src/Images/board.png");
        button.setIcon(icon);
        button.setBounds(0,0,frameWidth,750);
        button.addActionListener(winClick);

        button1.setFocusable(false);
        button1.setFont(new Font("Segoe Script", Font.BOLD, 40));
        button1.setForeground(Color.red);
        button1.setBackground(new Color(113, 169, 247));
        button1.addActionListener(winClick);
    }
    public static void playSound(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);

// Create an AudioStream object from the input stream.
        AudioStream as = new AudioStream(in);

// Use the static class member "player" from class AudioPlayer to play
// clip.
        AudioPlayer.player.start(as);

// Similarly, to stop the audio.
        //AudioPlayer.player.stop(as);
    }
    ActionListener winClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MainFrame.playSound("src/Images/click.wav");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    };
}
