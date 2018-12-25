import java.util.Collections;
import java.util.Set;

import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;

/**
 * A question with a text and an answer.
 */
public class Question {
    private String text;
    private String answer;

    /**
     * Constructs a question with empty question and answer.
     */
    public Question() {
        text = "";
        answer = "";
    }

    public void addText(String text) {
        this.text += text;
    }

    /**
     * Sets the question text.
     *
     * @param questionText the text of this question
     */
    public void setText(String questionText) {
        text = questionText;
    }

    /**
     * Sets the answer for this question.
     *
     * @param correctResponse the answer
     */
    public void setAnswer(String correctResponse) {
        answer = correctResponse;
    }

    /**
     * Checks a given response for correctness.
     *
     * @param response the response to check
     * @return true if the response was correct, false otherwise
     */
    public boolean checkAnswer(String response) {
        return response.toLowerCase().equals(answer.toLowerCase());
    }

    /**
     * Displays this question.
     */
    public void display() {
        System.out.println(text);
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}

class NumbericQuestion extends Question {

    private double correctResponse;

    @Override
    public boolean checkAnswer(String response) {
        return abs(parseDouble(response) - correctResponse) <= 0.01;
    }

    @Override
    public void setAnswer(String correctResponse) {
        super.setAnswer(correctResponse);
        this.correctResponse = parseDouble(correctResponse);
    }
}

class FillInQuestion extends Question {

    public FillInQuestion(String text) {
        String answer = text.substring(text.indexOf('_') + 1, text.lastIndexOf('_'));
        text = text.replaceAll("_\\w+_", "______");
        setText(text);
        setAnswer(answer);
    }

}

class AnyCorrectChoiceQuestion extends Question {

    private Set<String> correct;

    @Override
    public void setText(String questionText) {
        super.setText(questionText + " (This question allows multiple correct choices," +
                " you should provide any one of the correct choices)");
    }

    @Override
    public void setAnswer(String correctResponse) {
        super.setAnswer(correctResponse);
        Collections.addAll(correct, correctResponse.split(" "));
    }

    @Override
    public boolean checkAnswer(String response) {
        return correct.contains(response);
    }
}


class MultiChoiceQuestion extends Question {

    private Set<String> correct;

    @Override
    public void setText(String questionText) {
        super.setText(questionText + " (This question allows multiple correct choices," +
                " you should provide all correct choices)");
    }

    @Override
    public void setAnswer(String correctResponse) {
        super.setAnswer(correctResponse);
        Collections.addAll(correct, correctResponse.split(" "));
    }

    @Override
    public boolean checkAnswer(String response) {
        String[] s1 = response.split(" ");
        for (String s : s1) {
            if (!correct.contains(s))
                return false;
        }
        return s1.length == correct.size();
    }
}

class ChoiceQuestion extends Question {

    private int choiceCount = 0;

    public void addChoice(String choice, boolean correct) {
        int id = ++choiceCount;
        addText(id + ": " + choice);
        if (correct) {
            setAnswer(choice);
        }
    }

    @Override
    public String toString() {
        return "ChoiceQuestion{" +
                super.toString() +
                "choiceCount=" + choiceCount +
                '}';
    }
}

