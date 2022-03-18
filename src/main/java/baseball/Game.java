package baseball;

public interface Game {
    default public void run() {
        setup();
        while (!isWin()) {
            move();
        }
        end();
    }

    public void setup();

    public void move();

    public boolean isWin();

    public void end();
}
