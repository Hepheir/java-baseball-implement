package baseball;

public enum Command {
    NEW_GAME("1"),
    END_GAME("2");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
};
