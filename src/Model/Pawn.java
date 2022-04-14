package Model;

public class Pawn extends Piece{
    public static Pawn whitePawn,blackPawn;

    public Pawn(Place place, boolean isWhite) {
        super(place, isWhite);
        type=PieceType.Pawn;
        if(isWhite) whitePawn=this;
        else blackPawn=this;
    }

    @Override
    public void checkValidMoves(){
        validPlaces.clear();
        if(place.equals(Place.out)) return;
        if(! hasPromoted){
            int k=isWhite?1:-1;
            Place.setValidPlace(0,k,this);
        }
        else {
            int k=-1,l=1;
            if(! isWhite){
                k=-k; l=-l;
            }
            for (int i = -1; i <2 ; i++) {
                for (int j = -1; j <2 ; j++) {
                    if((!((i==0&&j==0)||(i==k&&j==k)||(i==l&&j==k)))){
                        Place.setValidPlace(i,j,this);
                    }
                }
            }
        }
    }
}
