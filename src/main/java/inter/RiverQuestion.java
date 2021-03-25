package inter;

/**
 * @author : Alex
 * @created : 07.03.2021, воскресенье
 **/
public class RiverQuestion implements Quiz.QuizQuestion {
    @Override
    public String getQuestion() {
        return "Самая длинная река в Росии?";
    }

    @Override
    public String[] getAnswerOptions() {
        return new String[]{"Волга","Амур","Енисей","Иртыш"};
    }

    @Override
    public Quiz.Result getAnswer(int option) {
        return option==2? Quiz.Result.RIGHT: Quiz.Result.WRONG;
        //Енисей
    }
}
