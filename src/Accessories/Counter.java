package Accessories;

/**
 * Counter class.
 */
public class Counter {
    private int counter;

    /**
     * Constructor for Counter.
     * @param value the value we want the counter to begin with
     */
    public Counter(int value) {
        this.counter = value;
    }

    /**
     * Method to increase the current count.
     * @param number the number we increase by
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Method to decrease the current count.
     * @param number the number we decrease by
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Method to get the current count.
     * @return the current count
     */
    public int getValue() {
        return counter;
    }
}
