package qna;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ValidatorTest {

    @Test
    void test_validateDeleteAuth() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Validator.validateDeleteAuth(false))
                .withMessageMatching(Validator.NO_AUTH_OF_DELETE_MESSAGE);
    }
}
