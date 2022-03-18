package baseball;

public class View {
    private static final String uiTextBalls = "%d볼";
    private static final String uiTextStrikes = "%d스트라이크";
    private static final String uiTextSeperator = " ";
    private static final String uiTextFourBalls = "낫싱";
    private static final String uiTextFormatGameEnd = "%d개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String uiTextEnterNumbers = "숫자를 입력해주세요 : ";
    private static final String uiTextHelp = "게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.";

    public static void printPlayerInputMessage() {
        System.out.print(uiTextEnterNumbers);
    }

    public static void printStatistic(int strikes, int balls) {
        if (strikes == 0 && balls == 0) {
            printFourBallMessage();
        } else {
            printStrikesAndBallsMessage(strikes, balls);
        }
    }

    private static void printFourBallMessage() {
        System.out.println(uiTextFourBalls);
    }

    private static void printStrikesAndBallsMessage(int strikes, int balls) {
        System.out.println(buildStrikesAndBallsMessage(strikes, balls));
    }

    private static String buildStrikesAndBallsMessage(int strikes, int balls) {
        String message = new String();
        if (balls > 0) {
            message = message.concat(String.format(uiTextBalls, balls));
        }
        if (strikes > 0 && balls > 0) {
            message = message.concat(uiTextSeperator);
        }
        if (strikes > 0) {
            message = message.concat(String.format(uiTextStrikes, strikes));
        }
        return message;
    }

    public static void printGameEndMessage(int strikes) {
        String message = String.format(uiTextFormatGameEnd, strikes);
        System.out.println(message);
    }

    public static void printHelpMessage() {
        String message = String.format(uiTextHelp, Command.NEW_GAME.getValue(), Command.END_GAME.getValue());
        System.out.println(message);
    }
}
