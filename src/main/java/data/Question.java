package data;

import data.interfaces.IQuestion;
import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionLayout;

import java.util.List;
import java.util.UUID;

/**
 * Question, defined by various Components. Components will be added vertical to the Question Layout. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a value, defines its difficulty.
 */
public class Question implements IQuestion {

    private UUID id;
    private int value;
    private IQuestionLayout questionLayout;
    private IQuestionLayout answerLayout;

    // region Constructors

    /**
     * Constructor for Object Stream
     *
     * @param id             Question id
     * @param value          Question value
     * @param questionLayout Question Pane Layout
     * @param answerLayout   Answer Pane Layout
     */
    public Question(UUID id, int value, IQuestionLayout questionLayout, IQuestionLayout answerLayout) {
        this.id = id;
        this.value = value;
        this.questionLayout = questionLayout;
        this.answerLayout = answerLayout;
    }

    /**
     * Create new Question with custom Layout and value
     *
     * @param value              value of the Question
     * @param questionComponents List with Question Components
     */
    public Question(int value, List<IQuestionComponent> questionComponents) {
        id = UUID.randomUUID();
        this.value = value;
        this.questionLayout = new QuestionLayout();
        if (questionComponents != null) {
            this.questionLayout.setQuestionComponents(questionComponents);
        }
        this.answerLayout = new QuestionLayout();
    }

    /**
     * Create a new Question with custom Layout and no value
     *
     * @param questionComponents List with Question Components
     */
    public Question(List<IQuestionComponent> questionComponents) {
        this(0, questionComponents);
    }

    /**
     * Create new Question with value
     *
     * @param value value of the Question
     */
    public Question(int value) {
        this(value, null);
    }

    public Question() {
        this(0, null);
    }

    // endregion

    /**
     * get the Answer Layout with all Question Components ordered
     *
     * @return all Answer Components
     */
    @Override
    public IQuestionLayout getAnswerLayout() {
        return answerLayout;
    }

    /**
     * get unique Id of this Question
     *
     * @return Question Id
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * get all Question Components ordered
     *
     * @return all Question Components
     */
    @Override
    public IQuestionLayout getQuestionLayout() {
        return questionLayout;
    }

    /**
     * value of Question. The Team, which correctly answered this Question will get the amount of this value as Points.
     *
     * @return value of Question
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * set the value for this Question.
     *
     * @param value value of Question
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

}
