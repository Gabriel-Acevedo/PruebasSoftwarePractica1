/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lasdamas.controllers.PlayController;
import lasdamas.models.Color;
import lasdamas.models.Coordinate;
import lasdamas.models.Game;
import lasdamas.models.Piece;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Gabri
 */
@RunWith(Parameterized.class)
public class PlayControllerTest {
    
    Game game;
    PlayController playController;
    Coordinate origin;
    Coordinate target;
    Coordinate adjacent;
    Coordinate backWards;
    Piece pieceOrigin;
    Piece pieceTarget;
    Piece pieceAdjacent;
    
    public int filaOr;
    public int columnaOr;
    public int filaTarg;
    public int columnaTarg;
    public int filaAdy;
    public int columnaAdy;
    public int filaBackWards;
    public int columnaBackWards;
    public Color colorTurno;
    public Color colorEnemigo;
    
    public PlayControllerTest(int filaOr , int columnaOr, int filaTarg, int columnaTarg, int filaAdy, int columnaAdy , 
                              Color colorTurno, Color colorEnemigo, int filaBackWards, int columnaBackWards) 
    {
        this.filaOr = filaOr;
        this.columnaOr = columnaOr;
        this.filaTarg = filaTarg;
        this.columnaTarg = columnaTarg;
        this.filaAdy =  filaAdy;
        this.columnaAdy = columnaAdy;
        this.colorTurno = colorTurno;
        this.colorEnemigo = colorEnemigo;
        this.filaBackWards = filaBackWards;
        this.columnaBackWards = columnaBackWards;
                
    }
    
    @Before
    public void iniciar(){
        game = new Game();
        playController = new PlayController(game);
        pieceTarget = new Piece();
        pieceAdjacent = new Piece();
        this.origin = new Coordinate(this.filaOr,this.columnaOr);
        this.target = new Coordinate(this.filaTarg,this.columnaTarg); 
        this.adjacent = new Coordinate(this.filaAdy,this.columnaAdy);
        this.backWards = new Coordinate(this.filaBackWards,this.columnaBackWards);
        this.pieceOrigin = playController.getPiece(this.origin);
        this.pieceTarget = playController.getPiece(this.target);
        this.pieceAdjacent = playController.getPiece(this.adjacent);
    }
    
    @Parameters
    public static Collection<Object[]> TestParameter(){
        Object[][] param = new Object[][]{ {2, 1, 3, 2, 4, 5, Color.WHITE, Color.BLACK, 1, 2} , 
                                           {6, 1, 5, 2, 5, 4, Color.BLACK, Color.WHITE, 7, 2} 
                                         };
        return Arrays.asList(param);
    }
    
        
    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        assertNull(playController.move(this.origin, this.target));
        assertNull(playController.getPiece(this.origin));
        assertNotNull(this.pieceTarget);
        assertEquals(this.pieceTarget.getColor(), this.colorTurno);
    }
    
    
    @Test
    public void givenPlayControllerWhenMovementRequiereNotCorrectThenError() {
        assertNotNull(this.pieceOrigin);
        assertEquals(this.pieceOrigin.getColor(),this.colorTurno);
        assertNotNull(this.pieceTarget);
        assertNotNull(playController.move(this.origin, this.target));
    }
    
    
    @Test
    public void givenPlayControllerWhenMovementBackWardsThenError() {
        assertNotNull(this.pieceOrigin);
        assertEquals(this.pieceOrigin.getColor(),this.colorTurno);
        assertFalse(this.pieceOrigin.isDama());
        assertNotNull(playController.move(this.origin, this.backWards));
    }
   
    
    @Test
    public void givenPlayerControllerWhenDamaMovementThenNotError(){
        assertNotNull(this.pieceOrigin);
        assertNull(this.pieceTarget);
        assertNotNull(this.pieceAdjacent);
        assertEquals(this.pieceAdjacent.getColor(), this.colorEnemigo);
        assertTrue(this.pieceOrigin.isDama());
        assertNull(playController.moveDama(this.origin, this.target));
        this.pieceOrigin = playController.getPiece(this.origin);
        this.pieceTarget =playController.getPiece(this.target);
        assertNull(this.pieceOrigin);
        assertNotNull(this.pieceTarget);
    }
    
    
    @Test
    public void givenPlayerControllerWhenDamaEatMovementThenNotError(){
        assertNotNull(this.pieceOrigin);
        assertNull(this.pieceTarget);
        assertNotNull(this.pieceAdjacent);
        assertEquals(this.pieceAdjacent.getColor(), this.colorEnemigo);
        assertTrue(this.pieceOrigin.isDama());
        assertNull(playController.moveDama(this.origin, this.target));
        List<Coordinate> adjTargetOrigin = new ArrayList();
        adjTargetOrigin = playController.getAdjacentsBetweenOriginTarget(this.origin, this.target);
        for(int i=0; i<adjTargetOrigin.size(); i++){
            assertNull(playController.removePiece(adjTargetOrigin.get(i)));
            this.pieceAdjacent = playController.getPiece(adjTargetOrigin.get(i));
            assertNull(this.pieceAdjacent);        
        }
        this.pieceOrigin = playController.getPiece(this.origin);
        this.pieceTarget =playController.getPiece(this.target);
        assertNull(this.pieceOrigin);
        assertNotNull(this.pieceTarget);
    }   
    
    
    @Test
    public void givenPlayControllerWhenEatPieceThenNotError() {   
        assertNull(this.pieceTarget);
        assertEquals(this.pieceOrigin.getColor(), this.colorTurno);
        assertNotNull(this.pieceAdjacent);
        assertEquals(this.pieceAdjacent.getColor(), this.colorEnemigo);
        assertNull(playController.move(this.origin, this.target));
        assertNull(playController.removePiece(this.adjacent));
        this.pieceOrigin = playController.getPiece(this.origin);
        this.pieceTarget =playController.getPiece(this.target);
        this.pieceAdjacent = playController.getPiece(this.adjacent);
        assertNull(this.pieceOrigin);
        assertNotNull(this.pieceTarget);
        assertNull(this.pieceAdjacent);
    }
    
    
    
    @Test
    public void givenPlayControllerWhenNotEatPieceThenError() {   
        assertNull(this.pieceTarget);
        assertEquals(this.pieceOrigin.getColor(), this.colorTurno);
        assertNotNull(this.pieceAdjacent);
        assertEquals(this.pieceAdjacent.getColor(), this.colorTurno);
        assertNotNull(playController.move(this.origin, this.target));
    }
    
    
    @Test
    public void givenPlayControllerWhenTargetCoordinateIsNullThenNotError() {
        assertTrue(playController.isTargetSquareEmpty(this.target));
    }
    
    @Test
    public void givenPlayControllerWhenOriginAndTargetAreSameThenNotError() {
        assertTrue(playController.isSameCoordinate(this.origin, this.target));     
    }
       
    
    @Test 
    public void givenPlaycontrollerWhenConvertPieceToDama(){
        assertNull(this.pieceTarget);
        assertNull(playController.move(this.origin, this.target));
        assertNull(this.pieceOrigin);
        this.pieceTarget = playController.getPiece(this.target);
        assertNotNull(this.pieceTarget);
        assertEquals(this.pieceTarget.getColor(), this.colorTurno);
        assertFalse(this.pieceTarget.isDama());
        assertTrue(this.pieceTarget.ConvertPieceToDama(this.target,this.colorTurno));
        assertNull(this.pieceTarget.NewDama());
        assertTrue(this.pieceTarget.isDama());  
    }
        
    
    @Test
    public void givenPlaycontrollerWhenCancelGame(){
        assertEquals(this.filaOr,-1);
    }
    
    
    @Test
    public void givenPlaycontrollerWhenEndGame(){
        assertEquals(playController.getPiecesLeft(this.colorTurno),0);
        assertFalse(playController.isGameBlocked(this.colorTurno));
    }

}

