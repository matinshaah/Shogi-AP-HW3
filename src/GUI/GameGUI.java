package GUI;

import Model.MakeItems;

import javax.swing.*;
import java.io.IOException;

public class GameGUI {
    public static void main(String[] args) throws IOException {
        new MakeItems();
        new GameGUI();
    }
    public static MainFrame mainFrame;
    public GameGUI(){
        SwingUtilities.invokeLater(() -> {
            try {
                mainFrame=new MainFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
