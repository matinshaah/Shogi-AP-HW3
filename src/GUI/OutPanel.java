package GUI;

import Actions.InputAndUpdate;
import Model.Piece;
import Model.PieceType;
import resources.ImageResource;
import resources.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static GUI.GameGUI.mainFrame;

public class OutPanel extends JPanel {
    static ArrayList<OutButton> allOutButtons= new ArrayList<>();
    ArrayList<OutButton> outButtons =new ArrayList<>();
    OutPanel(){
        setLayout(new GridLayout(5,2));
        setBackground(new Color(139, 98, 63));
        for (int i = 0; i < 10; i++) {
            OutButton button = new OutButton();
            allOutButtons.add(button);
            this.outButtons.add(button);
            this.add(button);
            //button.setBorder(BorderFactory.createLineBorder(Color.black));
            button.setFocusable(false);
            button.setBackground(new Color(139, 98, 63));
        }
        setImages();
    }
    void addPiece(Piece piece){
        for (OutButton outButton :
                outButtons) {
            if (outButton.piece == null) {
                outButton.piece = piece;
                break;
            }
        }
    }

    private static void setImages(){
        ImageResource imageResourceType=null;
        for (OutButton button:
                allOutButtons) {
            Piece piece =button.piece;
            if(piece==null) {
                button.setIcon(null);
                continue;
            }
            switch (piece.type){
                case SilverGeneral:
                    if(piece.isWhite){
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.whitePromotedSilver;
                        else
                            imageResourceType=ImageResource.whiteSilver;
                    }else {
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.blackPromotedSilver;

                        else
                            imageResourceType=ImageResource.blackSilver;
                    }
                    break;
                case GoldGeneral:
                    if(piece.isWhite)
                        imageResourceType=ImageResource.whiteGold;
                    else
                        imageResourceType=ImageResource.blackGold;
                    break;
                case Bishop:
                    if(piece.isWhite){
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.whitePromotedBishop;
                        else
                            imageResourceType=ImageResource.whiteBishop;
                    }else {
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.blackPromotedBishop;
                        else
                            imageResourceType=ImageResource.blackBishop;
                    }
                    break;
                case Lance:
                    if(piece.isWhite){
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.whitePromotedLance;
                        else
                            imageResourceType=ImageResource.whiteLance;
                    }else {
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.blackPromotedLance;
                        else
                            imageResourceType=ImageResource.blackLance;
                    }
                    break;
                case Pawn:
                    if(piece.isWhite){
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.whitePromotedPawn;
                        else
                            imageResourceType=ImageResource.whitePawn;
                    }else {
                        if(piece.hasPromoted)
                            imageResourceType=ImageResource.blackPromotedPawn;
                        else
                            imageResourceType=ImageResource.blackPawn;
                    }
                    break;
                case King:
                    if(piece.isWhite)
                        imageResourceType=ImageResource.whiteKing;
                    else
                        imageResourceType=ImageResource.blackKing;
                    break;
            }
            button.setIcon(new ImageIcon(ResourceManager.get(imageResourceType)));
        }
    }
    private static void updateClicking(){
        for (OutButton button :
                allOutButtons) {
            button.setEnabled(button.piece != null && (button.piece.isWhite ^ InputAndUpdate.isBlackTurn));
        }
    }
    static void checkIfWin() throws IOException {
        for (OutButton button :
                allOutButtons) {
            if(button.piece!=null&&button.piece.type== PieceType.King){
                String winner =button.piece.isWhite?"White":"Black";
                String endGame=winner+" wins!";
                mainFrame.showWinner(endGame);
            }
        }
    }
    public static void updateOutButtons() throws IOException {
        setImages();
        updateClicking();
        checkIfWin();
    }
    static class OutButton extends JButton{
        public static OutButton lastButton=null;
        Piece piece;
        OutButton(){
            if(!(this instanceof Cell)){
                this.addActionListener(e -> {
                    try {
                        MainFrame.playSound("src/Images/click.wav");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    setAlDisabled();
                    Cell.setAllDisabled();
                    OutButton button = (OutButton) (e.getSource());
                    for (Cell c :
                            MainFrame.cellList) {
                        if (c.place.piece == null) {
                            c.setEnabled(true);
                            c.setBackground(new Color(114, 25, 90));
                        }
                    }
                    lastButton = button;
                });
            }
        }
        public static void setAlDisabled(){
            for (OutButton b :
                    allOutButtons) {
                b.setEnabled(false);
            }
        }
    }
}
