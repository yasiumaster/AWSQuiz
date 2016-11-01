package dskoczny.awsquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static GameMode gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
    }

    public void quizButtonAction(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        gameMode = GameMode.QUIZ;
        startActivity(intent);
        finish();
    }

    public void practiceButtonAction(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        gameMode = GameMode.PRACTICE;
        startActivity(intent);
    }

    private void initDatabase() {
        QuizOpenHelper quizOpenHelper = QuizOpenHelper.getInstance(this);
        quizOpenHelper.openDataBase();
    }

    public static GameMode getGameMode() {
        assert gameMode != null;
        return gameMode;
    }

    public enum GameMode {
        QUIZ,
        PRACTICE
    }
}
