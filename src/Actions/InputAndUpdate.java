package Actions;
import Model.*;

import java.util.Scanner;

public class InputAndUpdate {
    static Scanner sc=new Scanner(System.in);
    public static boolean isBlackTurn=true;
    static Place firstPlace,secondPlace;
    static Piece firstPiece;
    static String[] str;
    public InputAndUpdate(){
        while (sc.hasNextLine()) {
            updateStatus();
            if (input()) {
                update();
                isBlackTurn = !isBlackTurn;
            }
            new Output();
        }
    }
    boolean input() {
        PieceType type;
        str = sc.nextLine().split("\\s+");
        firstPlace=null;secondPlace=null;
        if(str.length==1&&str[0].equals("0")) {
            System.out.print(Output.print);
            System.exit(0);
        }
        if(str.length!=3||str[1].length()!=2||str[2].length()!=2||str[0].length()!=1)
            return false;

        switch (str[0].toUpperCase().charAt(0)){
            case 'K':
                type= PieceType.King;
                break;
            case 'B':
                type= PieceType.Bishop;
                break;
            case 'G':
                type= PieceType.GoldGeneral;
                break;
            case 'S':
                type= PieceType.SilverGeneral;
                break;
            case 'L':
                type= PieceType.Lance;
                break;
            case 'P':
                type= PieceType.Pawn;
                break;
            default:
                return false;
        }

        if(! isNumber(str[2]))
            return false;
        secondPlace = Place.getPlaceByXAndY(str[2].charAt(0)-'0',str[2].charAt(1)-'0');

        if(isBlackTurn){
            if(Character.isUpperCase(str[0].charAt(0)))
                return false;

            if(str[1].equals("00")) {
                for (Piece p :
                        Piece.outPieces) {
                    if (p.type == type && (!p.isWhite)&&secondPlace.piece==null) {
                        firstPiece=p;
                        return true;
                    }
                }
                return false;
            }else if(isNumber(str[1])){
                firstPlace=Place.getPlaceByXAndY(str[1].charAt(0)-'0',str[1].charAt(1)-'0');
                if(firstPlace.piece==null) return false;
                if(firstPlace.piece.type==type&&(!firstPlace.piece.isWhite)){
                    return firstPlace.piece.validPlaces.contains(secondPlace);
                }
                return false;
            }else return false;

        }else {
            if(Character.isLowerCase(str[0].charAt(0)))
                return false;
            if(str[1].equals("00")) {
                for (Piece p :
                        Piece.outPieces) {
                    if (p.type == type && (p.isWhite)&&secondPlace.piece==null) {
                        firstPiece=p;
                        return true;
                    }
                }
                return false;
            }else if(isNumber(str[1])){
                firstPlace=Place.getPlaceByXAndY(str[1].charAt(0)-'0',str[1].charAt(1)-'0');
                if(firstPlace.piece==null) return false;
                if(firstPlace.piece.type==type&&(firstPlace.piece.isWhite)){
                    return firstPlace.piece.validPlaces.contains(secondPlace);
                }
                return false;
            }else return false;
        }

    }

    boolean isNumber(String str){  //1,2,...,5
        char ch0=str.charAt(0),ch1=str.charAt(0);
        return (ch0-'0')>0&&(ch0-'0'<6)&&(ch1-'0')>0&&(ch1-'0'<6);
    }

    void update(){
        if(str[1].equals("00")){
            firstPiece.place=secondPlace;
            secondPlace.piece=firstPiece;
            firstPiece.checkValidMoves();
            Piece.outPieces.remove(firstPiece);
        }
        else {
            if(secondPlace.piece!=null){
                secondPlace.piece.isWhite= !secondPlace.piece.isWhite;
                secondPlace.piece.hasPromoted =false;
                secondPlace.piece.place=Place.out;
                Piece.outPieces.add(secondPlace.piece);
                secondPlace.piece=null;
            }
            firstPiece=firstPlace.piece;
            firstPiece.checkPromotion(secondPlace);
            firstPiece.place=secondPlace;
            secondPlace.piece=firstPiece;
            firstPiece.checkValidMoves();
            firstPlace.piece=null;
        }
        firstPiece=null; firstPlace=null; secondPlace=null;
    }
    public static void updateStatus(){
        for (Piece p :
                Piece.piecesList) {
            p.checkValidMoves();
        }
    }
}