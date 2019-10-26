/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lasdamas.controllers;

import java.util.List;
import lasdamas.models.Color;
import lasdamas.models.Coordinate;
import lasdamas.models.Dama;
import lasdamas.models.Game;
import lasdamas.models.Piece;

/**
 *
 * @author Gabri
 */
public class PlayController extends AcceptController{
    
    Piece piece = new Piece();
    
    
    public PlayController(Game game) {
    }

    public Error move(Coordinate origin, Coordinate target){
        return null;
    }
    
    public Error moveDama(Coordinate origin, Coordinate target){
        return null;
    }

    public Piece getPiece(Coordinate origin) {  
        return this.piece;
    }
    
    public boolean isTargetSquareEmpty(Coordinate target){
        return true;
    }

    public Error removePiece(Coordinate adyacent) {
        return null;
    }
    
    public boolean isSameCoordinate(Coordinate origin, Coordinate target){
        return true;
    }
   
    
    public String getBoard() {
        return null;
    }
    
    public int getPiecesLeft(Color color){
        return 0;
    }
    
    public boolean isGameBlocked(Color color){
        return true;
    }

    public List<Coordinate> getAdjacentsBetweenOriginTarget(Coordinate origin, Coordinate target) {
       return null; 
    }
    
}
