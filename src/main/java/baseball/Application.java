package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application implements BaseballGame {
    final String CMD_NEW_GAME = "1";
    final String CMD_END_GAME = "2";
    final int AMOUNT_OF_NUMBERS = 3;
    final int NUMBER_RANGE_START_INCLUSIVE = 1;
    final int NUMBER_RANGE_END_INCLUSIVE = 9;
    int[] hostNumbers = {-1, -1, -1};
    int[] playerNumbers = {-1, -1, -1};

    public static void main(String[] args) {
        Application app = new Application();
        do {
            app.start();
        } while (app.promptStartNewGame());
    }

    private boolean promptStartNewGame() throws IllegalArgumentException {
        System.out.printf("게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.", CMD_NEW_GAME, CMD_END_GAME);
        System.out.println();
        String rawUserInput = getPlayerInput();
        if (rawUserInput.equals(CMD_NEW_GAME)) {
            return true;
        }
        if (rawUserInput.equals(CMD_END_GAME)) {
            return false;
        }
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
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

    @Override
    public void move() throws IllegalArgumentException {
        promptPlayerNumbers();
        printResult();
    }

    private void promptPlayerNumbers() throws IllegalArgumentException {
        System.out.print("숫자를 입력해주세요 : ");
        String[] rawPlayerNumbers = getPlayerInput().split("");
        checkAmountOfNumbers(rawPlayerNumbers);
        parseRawPlayerNumbers(rawPlayerNumbers);
    }

    private String getPlayerInput() {
        return Console.readLine().trim();
    }

    private <T> void checkAmountOfNumbers(T[] numbers) throws IllegalArgumentException {
        if (numbers.length != AMOUNT_OF_NUMBERS) {
            throw new IllegalArgumentException("3개의 숫자를 입력하셔야합니다.");
        }
    }

    private void parseRawPlayerNumbers(String[] rawPlayerNumbers) throws IllegalArgumentException {
        for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            try {
                setPlayerNumber(i, Integer.parseInt(rawPlayerNumbers[i]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
            }
        }
    }
}

interface BaseballGame extends Game {
    public int getHostNumber(int index);

    public void setHostNumber(int index, int value);

    public int getPlayerNumber(int index);

    public void setPlayerNumber(int index, int value);

    public int countStrikes();

    public int countBalls();

    public void printResult();
}

interface Game {
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
