package Model;

import java.util.ArrayList;

public class Piece {
    public static ArrayList<Piece> piecesList=new ArrayList<>();
    public static ArrayList<Piece> outPieces= new ArrayList<>();
    public ArrayList<Place> validPlaces= new ArrayList<>();
    public boolean isWhite,hasPromoted;
    public Place place;
    public PieceType type;
    public Piece(Place place,boolean isWhite){
        piecesList.add(this);
        this.place=place;
        place.piece=this;
        this.isWhite=isWhite;
    }

    public void checkPromotion(Place place){
        if((isWhite&&place.y>3)||(! isWhite)&&place.y<3)
            hasPromoted=true;
    }

    public void checkValidMoves(){}
}
