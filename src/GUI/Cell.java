package GUI;

import Model.Piece;
import Model.Place;
import resources.ImageResource;
import resources.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Actions.InputAndUpdate.isBlackTurn;
import static GUI.MainFrame.*;

public class Cell extends OutPanel.OutButton {
    Piece piece;
    final Place place;
    Cell (int x,int y){
        this.place=Place.getPlaceByXAndY(x,y);
        this.piece=place==null?null:place.piece;
        this.setFocusable(false);
        //this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.addActionListener(e -> {
            Cell cell = (Cell) (e.getSource());
            if(lastButton==null) {
                try {
                    MainFrame.playSound("src/Images/click.wav");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                setAllDisabled();
                OutPanel.OutButton.setAlDisabled();
                assert cell.place != null;
                for (Place p :
                        cell.place.piece.validPlaces) {
                    for (Cell c :
                            MainFrame.cellList) {
                        if (c.place == p) {
                            c.setEnabled(true);
                            c.setBackground(new Color(114, 25, 90));
                        }
                    }
                }
                GameGUI.mainFrame.revalidate();
                GameGUI.mainFrame.repaint();
                lastButton=cell;
            }else {
                Piece firstPiece = lastButton.piece;
                if(lastButton instanceof Cell){
                    for (Cell c :
                            cellList) {
                        if(c.place==((Cell) lastButton).place){
                            assert c.place != null;
                            firstPiece=c.place.piece;
                        }
                    }
                }
                Place firstPlace = firstPiece.place;
                Piece secondPiece=cell.piece;
                Place secondPlace=cell.place;
                if(secondPiece!=null){
                    try {
                        MainFrame.playSound("src/Images/kick.wav");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    secondPiece.isWhite= !secondPiece.isWhite;
                    secondPiece.hasPromoted=false;
                    secondPiece.place=Place.out;
                    if(! secondPiece.isWhite) blackOutPanel.addPiece(secondPiece);
                    else whiteOutPanel.addPiece(secondPiece);
                }else {
                    try {
                        MainFrame.playSound("src/Images/click.wav");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                assert secondPlace != null;
                secondPlace.piece=firstPiece;
                cell.piece=firstPiece;
                firstPiece.place=secondPlace;
                if(lastButton instanceof Cell) {
                    if (isBlackTurn) {
                        if (secondPlace.y < 3) {
                            if(!firstPiece.hasPromoted) {
                                try {
                                    MainFrame.playSound("src/Images/promoted.wav");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }

                            firstPiece.hasPromoted = true;
                        }
                    } else if (secondPlace.y > 3) {
                        if(!firstPiece.hasPromoted) {
                            try {
                                MainFrame.playSound("src/Images/promoted.wav");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }

                        firstPiece.hasPromoted = true;
                    }
                }
                firstPlace.piece=null;
                if(lastButton instanceof Cell){
                    for (Cell c :
                            cellList) {
                        if(c.place==((Cell) lastButton).place){
                            c.piece=null;
                            break;
                        }
                    }
                }
                lastButton.piece=null;
                lastButton=null;
                isBlackTurn = !isBlackTurn;
                try {
                    GameGUI.mainFrame.Update();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    static void setAllDisabled(){
        for (Cell c :
                cellList) {
            c.setEnabled(false);
        }
    }
    private static void setImages(){
        ImageResource imageResourceType=null;
        for (Cell c:
             MainFrame.cellList) {
            Piece piece =c.place.piece;
            if(piece==null) {
                c.setIcon(null);
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
            c.setIcon(new ImageIcon(ResourceManager.get(imageResourceType)));
        }
    }
    private static void setColorBackGround(){
        for (Cell cell :
                MainFrame.cellList) {
            Color color =(cell.place.x+cell.place.y)%2==0?new Color(253, 251, 223):new Color(103, 52, 29);
            cell.setBackground(color);
        }
    }
    private static void updateClicking(){
        for (Cell c :
                MainFrame.cellList) {
            if(c.place.piece==null)
                c.setEnabled(false);
            else {
                if(c.place.piece.isWhite ^ isBlackTurn){
                    c.setEnabled(c.place.piece.validPlaces.size() != 0);
                }else c.setEnabled(false);
            }
        }
    }
    public static void updateCells(){
        setColorBackGround();
        setImages();
        updateClicking();
    }

}
