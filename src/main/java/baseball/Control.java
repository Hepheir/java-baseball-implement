package baseball;

import java.util.ArrayList;

import camp.nextstep.edu.missionutils.Console;

public class Control {
    public static ArrayList<Integer> getNIntegersInRange(int startInclusive, int endInclusive, int count)
            throws IllegalArgumentException {
        View.printPlayerInputMessage();
        ArrayList<Integer> numbers = parseIntegers(Console.readLine().trim());
        checkAmountOfNumber(numbers, count);
        numbers.forEach((n) -> {
            checkIsInRange(n, startInclusive, endInclusive);
        });
        return numbers;
    }

    private static ArrayList<Integer> parseIntegers(String rawString) throws IllegalArgumentException {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (String rawNumber : rawString.split("")) {
            checkIsNumber(rawNumber);
            numbers.add(Integer.parseInt(rawNumber));
        }
        return numbers;
    }

    private static void checkIsNumber(String rawNumber) throws IllegalArgumentException {
        try {
            Integer.parseInt(rawNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    private static void checkIsInRange(int number, int startInclusive, int endInclusive) throws IllegalArgumentException {
        if (number < startInclusive || endInclusive < number) {
            throw new IllegalArgumentException("1~9 사이의 숫자만 입력할 수 있습니다.");
        }
    }

    private static void checkAmountOfNumber(ArrayList<Integer> numbers, int amount) throws IllegalArgumentException {
        if (numbers.size() != amount) {
            throw new IllegalArgumentException(String.format("%d개의 숫자를 입력하셔야합니다.", amount));
        }
    }

    public static Command getCommandFromInput() throws IllegalArgumentException {
        String rawUserInput = Console.readLine().trim();
        for (Command command : Command.values()) {
            if (rawUserInput.equals(command.getValue())) {
                return command;
            }
        }
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }
}
