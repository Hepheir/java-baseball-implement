package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.ArrayList;

class ApplicationTest extends NsTest {

    @Test
    void 게임종료_후_재시작() {
        final List<Integer> firstNumbers = new ArrayList<>();
        firstNumbers.add(1);
        firstNumbers.add(3);
        firstNumbers.add(5);

        final List<Integer> secondNumbers = new ArrayList<>();
        secondNumbers.add(5);
        secondNumbers.add(8);
        secondNumbers.add(9);

        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                firstNumbers, secondNumbers
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 입출력_테스트_숫자부족() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 입출력_테스트_숫자과다() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1234"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 입출력_테스트_숫자아님() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("abc"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 입출력_테스트_공백() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(" "))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
