package qna;

public class Validator {
    public static String NO_AUTH_OF_DELETE_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    private Validator() {}

    public static void validateDeleteAuth(boolean isOwner) throws CannotDeleteException {
        if (!isOwner) {
            throw new CannotDeleteException(NO_AUTH_OF_DELETE_MESSAGE);
        }
    }
}
