package Model;

public class Bishop extends Piece{
    public static Bishop whiteBishop,blackBishop;
    public Bishop(Place place, boolean isWhite) {
        super(place, isWhite);
        type=PieceType.Bishop;
        if(isWhite) whiteBishop=this;
        else blackBishop=this;
    }


    @Override
    public void checkValidMoves() {
        validPlaces.clear();
        if(place.equals(Place.out)) return;

        if(isWhite){
            for (int k = 0; Place.isEmpty(place.x + k, place.y + k) || k == 0; k++) {
                Place.setValidPlace(k + 1, k + 1, this);
            }
            for (int k = 0; Place.isEmpty(place.x - k, place.y + k) || k == 0; k++) {
                Place.setValidPlace(-k - 1, k + 1, this);
            }
        }else{
            for (int k = 0; Place.isEmpty(place.x-k, place.y - k) || k == 0; k++) {
                Place.setValidPlace(-k-1, -k - 1, this);
            }
            for (int k = 0; Place.isEmpty(place.x+k, place.y - k) || k == 0; k++) {
                Place.setValidPlace(k+1, -k - 1, this);
            }
        }
        if (hasPromoted){
            if(! isWhite){
                for (int k = 0; Place.isEmpty(place.x + k, place.y + k) || k == 0; k++) {
                    Place.setValidPlace(k + 1, k + 1, this);
                }
                for (int k = 0; Place.isEmpty(place.x - k, place.y + k) || k == 0; k++) {
                    Place.setValidPlace(-k - 1, k + 1, this);
                }
            }else{
                for (int k = 0; Place.isEmpty(place.x-k, place.y - k) || k == 0; k++) {
                    Place.setValidPlace(-k-1, -k - 1, this);
                }
                for (int k = 0; Place.isEmpty(place.x+k, place.y - k) || k == 0; k++) {
                    Place.setValidPlace(k+1, -k - 1, this);
                }
            }

            Place.setValidPlace(1,0,this);
            Place.setValidPlace(-1,0,this);
            Place.setValidPlace(0,1,this);
            Place.setValidPlace(0,-1,this);
        }
    }
}
