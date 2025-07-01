package Interfaces;

import biuoop.DrawSurface;

import OverallGame.Game;
/**
 * The Interfaces.Sprite interface represents an object that can be drawn on.
 * a DrawSurface and can update itself over time.
 */
public interface Sprite {

    /**
     * Draws the sprite on a given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is used to update the state of the sprite.
     */
    void timePassed();

    /**
     * adds the current sprite to the game.
     * @param g is the game that the sprite will be added to
     */
    void addToGame(Game g);

}
