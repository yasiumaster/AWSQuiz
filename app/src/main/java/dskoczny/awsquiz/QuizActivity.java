package dskoczny.awsquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
