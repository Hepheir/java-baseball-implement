package baseball;

public interface BaseballGame extends Game {
    public int getHostNumber(int index);

    public void setHostNumber(int index, int value);

    public int getPlayerNumber(int index);

    public void setPlayerNumber(int index, int value);

    public int countStrikes();

    public int countBalls();

    public void printResult();
}
