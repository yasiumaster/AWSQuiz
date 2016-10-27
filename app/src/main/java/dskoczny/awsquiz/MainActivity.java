package dskoczny.awsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
    }

    public void switchButtonAction(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private void initDatabase() {
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        quizOpenHelper.openDataBase();
    }
}
