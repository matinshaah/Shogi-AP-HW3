package Model;

public class SilverGeneral extends Piece{
    public static SilverGeneral whiteSliver,blackSilver;
    public SilverGeneral(Place place, boolean isWhite) {
        super(place, isWhite);
        type=PieceType.SilverGeneral;
        if(isWhite) whiteSliver=this;
        else blackSilver= this;
    }

    @Override
    public void checkValidMoves(){
        this.validPlaces.clear();
        if(place.equals(Place.out)) return;
        int k= isWhite?-1:1;
        if(! hasPromoted) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((!((j == 0)||(i==0&&j==k)))) {
                        Place.setValidPlace(i,j,this);
                    }
                }
            }
        }
        else {
            for (int i = -1; i <2 ; i++) {
                for (int j = -1; j <2 ; j++) {
                    if(!(i==0&&j==0)){
                        Place.setValidPlace(i,j,this);
                        if(Place.isEmpty(place.x +i,place.y +j)){
                            Place.setValidPlace(2*i,2*j,this);
                        }
                    }
                }
            }
        }
    }
}
