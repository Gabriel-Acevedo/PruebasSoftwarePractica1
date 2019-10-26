/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lasdamas.models;

/**
 *
 * @author Gabri
 */
public class Piece {
    
    public Color getColor() {
        return Color.BLACK;
    }
    
    
    public Dama NewDama(){
        return null;
    }
    
    public boolean ConvertPieceToDama(Coordinate target, Color color){
        return true;
    }
    
    public boolean isDama(){
        return true;
    }
}
