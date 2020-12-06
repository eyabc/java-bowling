package qna.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private static Stream<Boolean> provideDeletedResult() {
        return Stream.of(true, false);
    }

    @ParameterizedTest
    @MethodSource("provideDeletedResult")
    void test_setDeleted(boolean deleted) {
        assertThat(Q1.setDeleted(deleted).isDeleted())
                .isEqualTo(deleted);
    }
}
