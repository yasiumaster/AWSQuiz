package dskoczny.awsquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public final static int MAX_ANSWERS = 6;
    private Context context;
    private int currentQuestionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        context = this;
        reloadAnswers();
        currentQuestionId = loadQuestionAndAnswers();
    }

    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.answer1:
                if (checked) {
                    Toast.makeText(context, "1 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer2:
                if (checked) {
                    Toast.makeText(context, "2 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer3:
                if (checked) {
                    Toast.makeText(context, "3 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer4:
                if (checked) {
                    Toast.makeText(context, "4 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer5:
                if (checked) {
                    Toast.makeText(context, "5 checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.answer6:
                if (checked) {
                    Toast.makeText(context, "6 checked", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void nextClickAction(View view) {
        List<Integer> checked = new ArrayList<>();
        for (int i = 1; i <= MAX_ANSWERS; i++) {
            boolean isChecked = ((CheckBox) findViewById(getCheckBoxId(i))).isChecked();
            if(isChecked) {
                checked.add(i);
            }
        }
//        StringBuilder finalChecked = new StringBuilder();
//        for(Integer i : checked) {
//            finalChecked.append(i + ", ");
//        }
//        Toast.makeText(context, finalChecked.toString(), Toast.LENGTH_LONG).show();
        reloadAnswers();
        currentQuestionId = loadQuestionAndAnswers();
        checkAnswers(checked);
    }

    private void checkAnswers(List<Integer> checked) {
        //TODO
        //getCorrect answers
        //comprare with checked
        //add points

    }

    private int loadQuestionAndAnswers() {
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        HashMap<HashMap<Integer,String>, List<String>> questionAndAnswers = quizOpenHelper.getRandomQuestionWithAnswers();
        if (questionAndAnswers != null) {
            HashMap<Integer,String> questionMap = questionAndAnswers.keySet().iterator().next();
            Integer id = questionMap.keySet().iterator().next();
            textView.setText(questionMap.get(id));
            loadAnswers(questionAndAnswers.get(questionMap));
            return id;
        } else {
            textView.setText("NOT AVAILABLE");
            return 0;
        }
    }

    private void loadAnswers(List<String> answersList) {
        for (int i = 0; i < answersList.size(); i++) {
            CheckBox checkBox = ((CheckBox) findViewById(getCheckBoxId(i+1)));
            checkBox.setText(answersList.get(i));;
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
}
