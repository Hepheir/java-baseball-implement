package baseball;

public interface Game {
    default public void run() {
        setup();
        while (!isWin()) {
            onTurn();
        }
        end();
    }

    public void onStart();

    public void onTurn();

    public boolean isWin();

    public void onEnd();

    default public void start() {
        onStart();
    }

    default public void end() {
        onEnd();
    }
}
