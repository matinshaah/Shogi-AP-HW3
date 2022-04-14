package Model;

public class GoldGeneral extends Piece{
    public static GoldGeneral whiteGold,blackGold;
    public GoldGeneral(Place place, boolean isWhite) {
        super(place, isWhite);
        type=PieceType.GoldGeneral;
        if(isWhite) whiteGold=this;
        else blackGold=this;
    }

    @Override
    public void checkValidMoves(){
        this.validPlaces.clear();
        if(place.equals(Place.out)) return;
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
