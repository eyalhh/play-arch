package OverallGame;

import Interfaces.HitListener;
import Listeners.BallRemover;
import Listeners.ScoreTrackingListener;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

import Listeners.BlockRemover;
import Collections.GameEnvironment;
import Accessories.Velocity;
import Collections.SpriteCollection;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import Accessories.Counter;

/**
 * The OverallGame.Game class represents the main game engine that manages the game's sprites and environment.
 * It initializes the game, handles game logic, and runs the game loop.
 */
public class Game {
    private Counter remainingBlocks;
    private Counter availableBalls;
    private Counter currentScore;
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private GUI gui = new GUI("title", 800, 600);

    /**
     * Constructs a OverallGame.Game object with an empty collection of sprites and a new game environment.
     */
    public Game() {
        this.remainingBlocks = new Counter(0);
        this.availableBalls = new Counter(0);
        this.currentScore = new Counter(0);
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        gameEnvironment.addCollidable(c);
    }

    /**
     * Adds a sprite to the collection of sprites in the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game. This method should be called before starting the game loop.
     * It sets up the GUI and any necessary game components.
     */
    public void initialize() {
        int height = 20;
        int width = 60;
        int startX = 790;
        int startY = 200;


        Color[] colors = {Color.darkGray, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};

        // Add the blocks
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        HitListener scoreTrackListener = new ScoreTrackingListener(currentScore);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9 - i; j++) {
                Rectangle rect = new Rectangle(new Point(startX - j * width, startY + i * height), width, height);
                Block block = new Block(rect, colors[i]);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackListener);
                remainingBlocks.increase(1);

            }
        }

        // Add the balls
        Ball firstBall = new Ball(new Point(400, 569), 3, Color.BLACK,
                new Velocity(0, -3), gameEnvironment);
        Ball secondBall = new Ball(new Point(400, 569), 3, Color.BLACK,
                new Velocity(0, -3.3), gameEnvironment);
        Ball thirdBall = new Ball(new Point(400, 569), 3, Color.BLACK,
                new Velocity(0, -3.7), gameEnvironment);
        firstBall.addToGame(this);
        secondBall.addToGame(this);
        thirdBall.addToGame(this);
        availableBalls.increase(3);
        // Add the walls

        Block upperWall = new Block(new Rectangle(new Point(0, 25), 10, 600), Color.GRAY);
        Block leftWall = new Block(new Rectangle(new Point(10, 25), 790, 10), Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(790, 25), 10, 600), Color.GRAY);
        upperWall.addToGame(this);
        leftWall.addToGame(this);
        rightWall.addToGame(this);

        // Add the death-region block
        HitListener ballRemover = new BallRemover(this, availableBalls);
        Block deathRegion = new Block(new Rectangle(new Point(0, 630), 800, 10), Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        // Add the paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 570), 100, 15),
                4, Color.YELLOW, gui.getKeyboardSensor());
        paddle.addToGame(this);

    }

    /**
     * Runs the game loop.
     * This method continuously draws the game's sprites, updates their states,
     * and manages the game's timing.
     */
    public void run() {

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (remainingBlocks.getValue() == 0) {
                currentScore.increase(100);
                gui.close();
                return;
            }
            if (availableBalls.getValue() == 0) {
                gui.close();
                return;
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.drawText(398, 14, "Score: " + currentScore.getValue(), 12);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removing a collidable object from SpriteCollection.
     * @param c the collidable object we want to remove
     */
    public void removeCollidable(Collidable c) {
        gameEnvironment.removeCollidable(c);
    }

    /**
     * Removing a sprite object from SpriteCollection.
     * @param s the sprite object we want to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
