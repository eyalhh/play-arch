package Interfaces;

/**
 * Interface for hit notifier objects.
 */
public interface HitNotifier {
    /**
     * Adds h1 as a listener to hit events.
     * @param h1 the hitListener
     */
    void addHitListener(HitListener h1);

    /**
     * Removes h1 from the list of listeners to hit events.
     * @param h1 the hitListener
     */
    void removeHitListener(HitListener h1);
}
