package qna.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void validateAuth() {
        assertThatExceptionOfType(UnAuthorizedException.class)
                .isThrownBy(() -> new Answer(null, QuestionTest.Q1, "Answers Contents1"));
    }

    @Test
    void validateExistenceOfQuestion() {
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> new Answer(UserTest.JAVAJIGI, null, "test"));
    }

    private static Stream<Arguments> provideAnswerAndOwnerResult() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI, true),
                Arguments.of(A2, UserTest.JAVAJIGI, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAnswerAndOwnerResult")
    void isOwner(Answer answer, User owner, boolean expect) {
        assertThat(answer.isOwner(owner)).isEqualTo(expect);
    }
}
