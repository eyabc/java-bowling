package qna.domain;

import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void validateAuth() {
        assertThatExceptionOfType(UnAuthorizedException.class)
                .isThrownBy(() -> new Answer(null, QuestionTest.Q1, "Answers Contents1"));
    }

}
