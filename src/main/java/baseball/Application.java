package baseball;

import camp.nextstep.edu.missionutils.Randoms;

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

    @Override
    public void setup() {
        generateRandomHostNumbers();
    }

    private void generateRandomHostNumbers() {
        for (int i = 0; i < 3; i++) {
            int newNumber;
            do {
                newNumber = Randoms.pickNumberInRange(1, 9);
                setHostNumber(i, newNumber);
            } while (isNumberInHostNumbers(newNumber, i));
        }
    }

    private boolean isNumberInHostNumbers(int number, int endExclusive) {
        for (int i = 0; i < endExclusive; i++) {
            if (getHostNumber(i) == number)
                return true;
        }
        return false;
    }

    @Override
    public int countBalls() {
        int ballsCnt = 0;
        for (int i = 0; i < 3; i++) {
            int playerNumber = getPlayerNumber(i);
            int numberFoundAt = findNumberFromHostNumbers(playerNumber);
            if (numberFoundAt != -1 && numberFoundAt != i) {
                ballsCnt++;
            }
        }
        return ballsCnt;
    }

    private int findNumberFromHostNumbers(int number) {
        // Returns -1, if not found.
        for (int i = 0; i < 3; i++) {
            if (getHostNumber(i) == number) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int countStrikes() {
        int strikesCnt = 0;
        for (int i = 0; i < 3; i++) {
            if (getHostNumber(i) == getPlayerNumber(i)) {
                strikesCnt++;
            }
        }
        return strikesCnt;
    }

    @Override
    public boolean isWin() {
        return countStrikes() == 3;
    }

    @Override
    public void printResult() {
        int strikeCnt = countStrikes();
        int ballCnt = countBalls();
        if (strikeCnt == 0 && ballCnt == 0) {
            System.out.print("낫싱");
        } else {
            if (ballCnt > 0) {
                System.out.printf("%d볼", ballCnt);
            }
            if (strikeCnt > 0 && ballCnt > 0) {
                System.out.print(" ");
            }
            if (strikeCnt > 0) {
                System.out.printf("%d스트라이크", strikeCnt);
            }
        }
        System.out.println();
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
