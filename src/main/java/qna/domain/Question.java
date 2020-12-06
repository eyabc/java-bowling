package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;

@Entity
public class Question extends AbstractEntity {
    public static String NO_AUTH_OF_DELETE_MESSAGE = "질문을 삭제할 권한이 없습니다.";
    public static String ALONG_WITH_ANSWERS_OF_OTHERS_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private final Answers answers = new Answers();

    private boolean deleted = false;

    public Question() { }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public User getWriter() {
        return writer;
    }

    private void validateAuthOfDeletion(boolean isOwner) throws CannotDeleteException {
        if (!isOwner) {
            throw new CannotDeleteException(NO_AUTH_OF_DELETE_MESSAGE);
        }
    }

    private void validateWriterOfDeletion(boolean isWroteByOwnerAll) throws CannotDeleteException {
        if (!isWroteByOwnerAll) {
            throw new CannotDeleteException(ALONG_WITH_ANSWERS_OF_OTHERS_MESSAGE);
        }
    }

    public void delete(User loginUser) throws CannotDeleteException {
        validateAuthOfDeletion(writer.equals(loginUser));
        validateWriterOfDeletion(answers.isWroteByOwnerAll(writer));

        setDeleted(true);
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
//        answers.add(answer);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
