package dskoczny.awsquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        TextView resultText = (TextView) findViewById(R.id.resultText);
        Double resultPercent = 0D;
        if (QuizActivity.getMark() > 0) {
            resultPercent = QuizActivity.getMark() / QuizActivity.getQuizQuestionCount() * 100;
        }
        resultText.setText("RESULT: " + resultPercent + " %");
    }

    public void finalButtonAction(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
