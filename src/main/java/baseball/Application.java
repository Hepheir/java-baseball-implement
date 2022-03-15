package baseball;

import camp.nextstep.edu.missionutils.Randoms;

public class Application implements Game {
    final int AMOUNT_OF_NUMBERS = 3;
    final int NUMBER_RANGE_START_INCLUSIVE = 1;
    final int NUMBER_RANGE_END_INCLUSIVE = 9;
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
        return 0 <= index && index < AMOUNT_OF_NUMBERS;
    }

    private boolean checkValue(int value) {
        return NUMBER_RANGE_START_INCLUSIVE <= value && value <= NUMBER_RANGE_END_INCLUSIVE;
    }

    @Override
    public void setup() {
        generateRandomHostNumbers();
    }

    private void generateRandomHostNumbers() {
        for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            int newNumber;
            do {
                newNumber = Randoms.pickNumberInRange(NUMBER_RANGE_START_INCLUSIVE, NUMBER_RANGE_END_INCLUSIVE);
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
        for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
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
        for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            if (getHostNumber(i) == number) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int countStrikes() {
        int strikesCnt = 0;
        for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            if (getHostNumber(i) == getPlayerNumber(i)) {
                strikesCnt++;
            }
        }
        return strikesCnt;
    }

    @Override
    public boolean isWin() {
        return countStrikes() == AMOUNT_OF_NUMBERS;
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

    @Override
    public void end() {
        System.out.printf("%d개의 숫자를 모두 맞히셨습니다! 게임 종료", AMOUNT_OF_NUMBERS);
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
