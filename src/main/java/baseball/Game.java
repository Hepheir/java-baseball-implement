package baseball;

public interface Game {
    default public void run() {
        setup();
        while (!isWin()) {
            onTurn();
        }
        end();
    }

    public void setup();

    public void onTurn();

    public boolean isWin();

    public void end();
}
