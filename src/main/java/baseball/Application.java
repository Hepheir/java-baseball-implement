package baseball;

public class Application implements Game {
    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        Application app = new Application();
        app.start();
    }
}

interface Game {
    public int getHostNumber(int index);

    public int setHostNumber(int index, int value);

    public int getPlayerNumber(int index);

    public int setPlayerNumber(int index, int value);

    default public void start() {
        setup();
        while (!isWin()) {
            move();
        }
        end();
    }

    public void setup();

    public void move();

    public int countStrikes();

    public int countBalls();

    public void printResult();

    public boolean isWin();

    public void end();
}
