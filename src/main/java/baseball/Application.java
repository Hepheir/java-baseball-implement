package baseball;

public class Application implements Game {
    int[] hostNumbers = {-1, -1, -1};
    int[] playerNumbers = {-1, -1, -1};

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        Application app = new Application();
        app.start();
    }

    @Override
    public int getHostNumber(int index) {
        assert checkIndex(index);
        return hostNumbers[index];
    }

    @Override
    public void setHostNumber(int index, int value) {
        assert checkIndex(index) && checkValue(value);
        hostNumbers[index] = value;
    }

    @Override
    public int getPlayerNumber(int index) {
        assert checkIndex(index);
        return playerNumbers[index];
    }

    @Override
    public void setPlayerNumber(int index, int value) {
        assert checkIndex(index) && checkValue(value);
        playerNumbers[index] = value;
    }

    private boolean checkIndex(int index) {
        return 0 <= index && index <= 2;
    }

    private boolean checkValue(int value) {
        return 1 <= value && value <= 9;
    }
}

interface Game {
    public int getHostNumber(int index);

    public void setHostNumber(int index, int value);

    public int getPlayerNumber(int index);

    public void setPlayerNumber(int index, int value);

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
