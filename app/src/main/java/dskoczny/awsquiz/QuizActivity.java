package dskoczny.awsquiz;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class QuizActivity extends AppCompatActivity {

    public final static int MAX_ANSWERS = 6;
    public final static List<Integer> powCollection = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 16, 32));
    private final static int QUIZ_QUESTION_COUNT = 2;//20
    private final static int TIME_FOR_QUIZ_IN_SEC = 30 * 60;
    private Context context;
    private int currentQuestionId = 0;
    private TextView timerView;
    private CountDownTimer countDownTimer;
    private Set<Integer> questionIdsSet = new HashSet<>();
    private HashMap<HashMap<Integer, String>, List<String>> questionAndAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        context = this;
        timerView = (TextView) findViewById(R.id.timer);
        reloadAnswers();
        if(MainActivity.getGameMode() == MainActivity.GameMode.QUIZ) {
            prepareQuiz(); //get 20 question, set timer and count-down counter
        }
        currentQuestionId = loadQuestionAndAnswers();
    }

    private void prepareQuiz() {
        Random r = new Random();
        int currentQuestionsCount = QuizOpenHelper.getInstance(context).getQuestionsCount();
        while (questionIdsSet.size() < QUIZ_QUESTION_COUNT) {
            int nr = r.nextInt(currentQuestionsCount + 1);
            if (nr != 0) {
                questionIdsSet.add(nr);
            }
        }
//        startTimer();
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        questionAndAnswers = quizOpenHelper.getFixedQuestionsWithAnswers(questionIdsSet);
    }

    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.answer1:
                if (checked) {
//                    Toast.makeText(context, "1 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer2:
                if (checked) {
//                    Toast.makeText(context, "2 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer3:
                if (checked) {
//                    Toast.makeText(context, "3 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer4:
                if (checked) {
//                    Toast.makeText(context, "4 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer5:
                if (checked) {
//                    Toast.makeText(context, "5 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer6:
                if (checked) {
//                    Toast.makeText(context, "6 checked", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void nextClickAction(View view) {
        List<Integer> checked = new ArrayList<>();
        for (int i = 1; i <= MAX_ANSWERS; i++) {
            boolean isChecked = ((CheckBox) findViewById(getCheckBoxId(i))).isChecked();
            if (isChecked) {
                checked.add(powCollection.get(i - 1));
            }
        }
        reloadAnswers();
        checkAnswers(checked);

        if(MainActivity.getGameMode() == MainActivity.GameMode.QUIZ && questionAndAnswers.isEmpty()) {
            Intent intent = new Intent(this, FinalActivity.class);
            startActivity(intent);
        }
        currentQuestionId = loadQuestionAndAnswers();
    }

    private void checkAnswers(List<Integer> checked) {
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        List<Integer> correctAnswers = quizOpenHelper.getCorrectAnswers(currentQuestionId);
        boolean isCorrectAnswered = sum(correctAnswers) == sum(checked);
        Toast.makeText(context, String.valueOf(isCorrectAnswered), Toast.LENGTH_LONG).show();
    }

    private int loadQuestionAndAnswers() {
        TextView textView = (TextView) findViewById(R.id.textView);
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        if (questionAndAnswers == null || questionAndAnswers.isEmpty()) {
            questionAndAnswers = quizOpenHelper.getRandomQuestionWithAnswers();
        }
        assert questionAndAnswers != null;
        HashMap<Integer, String> questionMap = questionAndAnswers.keySet().iterator().next();
        Integer id = questionMap.keySet().iterator().next();
        textView.setText(questionMap.get(id));
        loadAnswers(questionAndAnswers.get(questionMap));
        questionAndAnswers.remove(questionMap);
        return id;
    }

    private void loadAnswers(List<String> answersList) {
        for (int i = 0; i < answersList.size(); i++) {
            CheckBox checkBox = ((CheckBox) findViewById(getCheckBoxId(i + 1)));
            checkBox.setText(answersList.get(i));
            checkBox.setEnabled(true);
            checkBox.setVisibility(View.VISIBLE);
        }
    }

    private void reloadAnswers() {
        for (int i = 1; i <= MAX_ANSWERS; i++) {
            CheckBox checkBox = ((CheckBox) findViewById(getCheckBoxId(i)));
            checkBox.setEnabled(false);
            checkBox.setVisibility(View.INVISIBLE);
            checkBox.setChecked(false);
        }
    }

    private int getCheckBoxId(int i) {
        switch (i) {
            case 1:
                return R.id.answer1;
            case 2:
                return R.id.answer2;
            case 3:
                return R.id.answer3;
            case 4:
                return R.id.answer4;
            case 5:
                return R.id.answer5;
            case 6:
                return R.id.answer6;
        }
        throw new IllegalArgumentException();
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    private void startTimer() {
        timerView.setText(String.valueOf(TIME_FOR_QUIZ_IN_SEC));
        countDownTimer = new CountDownTimer(TIME_FOR_QUIZ_IN_SEC, 1000) {
            @Override
            public void onTick(long l) {
                timerView.setText(String.valueOf(l));
            }

            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();
    }
}
