package baseball;

public interface Game {
    default void run() {
        start();
        do {
            onTurn();
        } while (!isWin());
        end();
    }

    void onStart();

    void onTurn();

    boolean isWin();

    void onEnd();

    default void start() {
        onStart();
    }

    default void end() {
        onEnd();
    }
}
