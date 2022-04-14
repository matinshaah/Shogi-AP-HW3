package Model;

public class King extends Piece{
    public static King whiteKing,blackKing;
    public King(Place place, boolean isWhite) {
        super(place, isWhite);
        type=PieceType.King;
        if(isWhite) whiteKing=this;
        else blackKing=this;
    }
    @Override
    public void checkValidMoves(){
        this.validPlaces.clear();
        if(place.equals(Place.out)) return;

        for (int i = -1; i <2 ; i++) {
            for (int j = -1; j <2 ; j++) {
                if(!(i==0&&j==0))
                    Place.setValidPlace(i,j,this);
            }
        }
    }
}
