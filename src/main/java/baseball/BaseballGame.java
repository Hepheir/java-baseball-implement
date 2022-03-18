package baseball;

import java.util.ArrayList;

import camp.nextstep.edu.missionutils.Randoms;

public class BaseballGame implements Game {
    private final int AMOUNT_OF_NUMBERS = 3;
    private final int NUMBER_RANGE_START_INCLUSIVE = 1;
    private final int NUMBER_RANGE_END_INCLUSIVE = 9;
    private ArrayList<Integer> hostNumbers;
    private ArrayList<Integer> playerNumbers;

    @Override
    public void onStart() {
        setup();
    }

    private void setup() {
        hostNumbers = generateRandomNumbers();
    }

    private ArrayList<Integer> generateRandomNumbers() {
        ArrayList<Integer> generatedNumbers = new ArrayList<>();
        Randoms.pickUniqueNumbersInRange(
                NUMBER_RANGE_START_INCLUSIVE,
                NUMBER_RANGE_END_INCLUSIVE,
                AMOUNT_OF_NUMBERS)
            .forEach((n) -> {
                generatedNumbers.add(n);
            });;
        return generatedNumbers;
    }

    private int countBalls() {
        int ballsCnt = 0;
        for (Integer number : playerNumbers) {
            if (hostNumbers.contains(number)) {
                ballsCnt++;
            }
        }
        ballsCnt -= countStrikes();
        return ballsCnt;
    }

    private int countStrikes() {
        int strikesCnt = 0;
        for (int index = 0; index < AMOUNT_OF_NUMBERS; index++) {
            if (hostNumbers.get(index) == playerNumbers.get(index)) {
                strikesCnt++;
            }
        }
        return strikesCnt;
    }

    @Override
    public void onTurn() throws IllegalArgumentException {
        playerNumbers = Control.getNIntegersInRange(
                NUMBER_RANGE_START_INCLUSIVE,
                NUMBER_RANGE_END_INCLUSIVE,
                AMOUNT_OF_NUMBERS);
        int strikes = countStrikes();
        int balls = countBalls();
        View.printStatistic(strikes, balls);
    }

    @Override
    public boolean isWin() {
        return countStrikes() == AMOUNT_OF_NUMBERS;
    }

    @Override
    public void onEnd() {
        View.printGameEndMessage(AMOUNT_OF_NUMBERS);
    }

    public void runMultipleGames() {
        while (true) {
            run();
            View.printHelpMessage();
            if (Control.getCommandFromInput() == Command.END_GAME) {
                break;
            }
        };
    }
}
