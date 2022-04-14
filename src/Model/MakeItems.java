package Model;

import Actions.InputAndUpdate;

public class MakeItems {
    public MakeItems(){
        main();
    }

    void main() {
        for (int x = 1; x <6 ; x++) {
            for (int y = 1; y <6 ; y++) {
                new Place(x,y);
            }
        }
        new Pawn(Place.getPlaceByXAndY(1,2),true);
        new Pawn(Place.getPlaceByXAndY(5,4),false);
        new King(Place.getPlaceByXAndY(1,1),true);
        new King(Place.getPlaceByXAndY(5,5),false);
        new GoldGeneral(Place.getPlaceByXAndY(2,1),true);
        new GoldGeneral(Place.getPlaceByXAndY(4,5),false);
        new SilverGeneral(Place.getPlaceByXAndY(3,1),true);
        new SilverGeneral(Place.getPlaceByXAndY(3,5),false);
        new Bishop(Place.getPlaceByXAndY(4,1),true);
        new Bishop(Place.getPlaceByXAndY(2,5),false);
        new Lance(Place.getPlaceByXAndY(5,1),true);
        new Lance(Place.getPlaceByXAndY(1,5),false);
        InputAndUpdate.updateStatus();
    }
}
