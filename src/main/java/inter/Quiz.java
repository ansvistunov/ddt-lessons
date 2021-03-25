package inter;

/**
 * @author : Alex
 * @created : 07.03.2021, воскресенье
 **/
public interface Quiz {
    enum Result {
        RIGHT, WRONG
    }

    interface QuizQuestion{
        String getQuestion(); //возвращает вопрос
        String[] getAnswerOptions(); //возвращает варианты ответа
        Result getAnswer(int option); //принимает ответ
    }

    void startQuiz();
    boolean hasNextQuestion();
    QuizQuestion nextQuestion();
}
