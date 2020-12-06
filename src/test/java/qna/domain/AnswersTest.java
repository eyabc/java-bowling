package qna.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    Answers answers;

    private static Stream<Arguments> provideAnswersAndOwnerResult() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "content1"),
                                new Answer(UserTest.SANJIGI, QuestionTest.Q1, "content2")
                        ),
                        UserTest.JAVAJIGI,
                        false
                ),
                Arguments.of(
                        Arrays.asList(
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "content1"),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "content2")
                        ),
                        UserTest.JAVAJIGI,
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideAnswersAndOwnerResult")
    void isWroteByOwnerAll(List<Answer> answers, User owner, boolean expect) {
        assertThat(new Answers(answers).isWroteByOwnerAll(owner)).isEqualTo(expect);
    }


}
