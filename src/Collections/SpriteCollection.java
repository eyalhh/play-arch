package Collections;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Collections.SpriteCollection class represents a collection of sprites.
 * It provides methods to manage and interact with sprites in the collection.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Constructs a Collections.SpriteCollection object with an empty sprites array list.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     * This method updates the state of all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<>(sprites);
        for (Sprite s : copySprites) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on a given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copySprites = new ArrayList<>(sprites);
        for (Sprite s : copySprites) {
            s.drawOn(d);
        }
    }

    /**
     * Removing a sprite object from SpriteCollection.
     * @param s the sprite object we want to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

}
