package GUI;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    Map(){
        setSize(new Dimension(750,750));
        this.setLayout(new GridLayout(5,5));
        for (int i = 5; i > 0; i--) {
            for (int j = 1; j <6 ; j++) {
                Cell cell=new Cell( j, i);
                MainFrame.cellList.add(cell);
                this.add(cell);
            }
        }
        Cell.updateCells();
    }
}
