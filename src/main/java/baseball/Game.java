package baseball;

public interface Game {
    default public void start() {
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
