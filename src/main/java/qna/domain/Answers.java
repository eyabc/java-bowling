package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isWroteByOwnerAll(User owner) {
        return !answers.stream()
                .anyMatch(answer -> !answer.isOwner(owner));
    }

}
