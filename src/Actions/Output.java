package Actions;

import Model.Piece;
import Model.Place;

public class Output {
    public static String print="";
    public Output(){
        for (int i=1;i<6;i++){
            for (int j = 1; j < 6; j++) {
                Place p =Place.getPlaceByXAndY(j,i);
                assert p != null;
                if (p.piece == null) print+="-";
                else print+=letter(p.piece);
            }
        }
        print+="\n";
        StringBuilder blackPieces= new StringBuilder();
        StringBuilder whitePieces= new StringBuilder();
        for (Piece p :
                Piece.outPieces) {
            if(p.isWhite) whitePieces.append(letter(p));
            else blackPieces.append(letter(p));
        }
        print+=blackPieces+"\n";
        print+=whitePieces+"\n";
        if(blackPieces.length()!=0) {
            for (int i = 0; i < blackPieces.length(); i++) {
                if (blackPieces.charAt(i) == 'k') {
                    print+="black wins!";
                    System.out.print(print);
                    System.exit(0);
                }
            }
        }
        if(whitePieces.length()!=0) {
            for (int i = 0; i < whitePieces.length(); i++) {
                if (whitePieces.charAt(i) == 'K') {
                    print+="white wins!";
                    System.out.print(print);
                    System.exit(0);
                }
            }
        }
    }

    static char letter(Piece p){
        char ch='-';
        switch (p.type){
            case King:
                ch='k';
                break;
            case Pawn:
                ch='p';
                break;
            case Lance:
                ch='l';
                break;
            case Bishop:
                ch='b';
                break;
            case GoldGeneral:
                ch='g';
                break;
            case SilverGeneral:
                ch='s';
                break;
        }
        if(p.isWhite) return Character.toUpperCase(ch);
        return ch;
    }
}
