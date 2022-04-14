package Model;

public class Lance extends Piece{
    public static Lance whiteLance,blackLance;
    public Lance(Place place, boolean isWhite) {
        super(place, isWhite);
        type=PieceType.Lance;
        if(isWhite) whiteLance=this;
        else blackLance=this;
    }

    @Override
    public void checkValidMoves(){
        validPlaces.clear();
        if(place.equals(Place.out)) return;
        if(isWhite) {
            for (int j = 0; Place.isEmpty(place.x, place.y + j) || j == 0; j++) {
                Place.setValidPlace(0, j + 1, this);
            }
        }else {
            for (int j = 0; Place.isEmpty(place.x, place.y + j) || j == 0; j--) {
                Place.setValidPlace(0, j - 1, this);
            }
        }
        if(hasPromoted){
            for (int j = 0; Place.isEmpty(place.x, place.y + j)||j==0 ; j++) {
                Place.setValidPlace(0,j+1,this);
            }
            for (int j = 0; Place.isEmpty(place.x, place.y + j)||j==0 ; j--) {
                Place.setValidPlace(0,j-1,this);
            }
            for (int i = 0; Place.isEmpty(place.x + i, place.y)|| i ==0 ; i++) {
                Place.setValidPlace(i+1, 0,this);
            }

            for (int i = 0; Place.isEmpty(place.x + i, place.y)|| i ==0 ; i--) {
                Place.setValidPlace(i-1, 0,this);
            }
        }
    }
}
