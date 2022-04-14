package Model;


import java.util.ArrayList;

public class Place {
    public static ArrayList<Place> placeList=new ArrayList<>();
    public static Place out =new Place(0,0);
    public int x,y;
    public Piece piece;
    public Place(int x, int y){
        placeList.add(this);
        this.x=x;
        this.y=y;
        piece=null;

    }
    static boolean isValid(int x,int y){
        return x>0&&x<6&&y>0&&y<6;
    }

    public static Place getPlaceByXAndY(int x, int y){
        for (Place p :
                placeList) {
            if(p.x==x&&p.y==y) return p;
        }
        return null;
    }
    public static boolean isEmpty (int x, int y){
        for (Place p :
                placeList) {
            if(p.x==x&&p.y==y&&p.piece==null) return true;
        }
        return false;
    }

    public static void setValidPlace(int i,int j,Piece piece) {
        if (Place.isValid(piece.place.x + i, piece.place.y + j)) {
            Place p = Place.getPlaceByXAndY(piece.place.x + i, piece.place.y + j);
            assert p != null;
            if (p.piece == null || (p.piece.isWhite ^ piece.isWhite))
                piece.validPlaces.add(p);
        }
    }

    public boolean equals(Place other){
        if(other==null||getClass()!=other.getClass())
            return false;
        else {
            return x==other.x && y==other.y;
        }
    }


}
