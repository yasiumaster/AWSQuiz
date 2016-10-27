package dskoczny.awsquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private final int MAX_ANSWERS = 4;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_quiz);
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        String question = quizOpenHelper.getRandomQuestion();
        if (question != null) {
            textView.setText(question);
        } else {
            textView.setText("NOT AVAILABLE");
        }
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
        StringBuilder finalChecked = new StringBuilder();
        for(Integer i : checked) {
            finalChecked.append(i + ", ");
        }

        Toast.makeText(context, finalChecked.toString(), Toast.LENGTH_LONG).show();
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
        }
        throw new IllegalArgumentException();
    }
}
