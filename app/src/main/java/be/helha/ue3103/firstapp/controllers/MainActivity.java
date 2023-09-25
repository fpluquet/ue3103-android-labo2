package be.helha.ue3103.firstapp.controllers;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.helha.ue3103.firstapp.R;
import be.helha.ue3103.firstapp.models.QuestionsBank;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QUIZ_APP";
    public static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;
    private QuestionsBank mQuestionBank = new QuestionsBank();

    private ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                boolean isAnswerShown = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
                if (isAnswerShown) {
                    Toast.makeText(this, R.string.answer_has_been_shown, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mQuestionBank.setCurrentIndex(savedInstanceState.getInt(KEY_INDEX));
        }
        mTrueButton = this.findViewById(R.id.true_button);
        mFalseButton = this.findViewById(R.id.false_button);
        mNextButton = this.findViewById(R.id.next_button);
        mQuestionTextView = this.findViewById(R.id.question_text_view);
        mCheatButton = this.findViewById(R.id.cheat_button);
        setQuestionText();
        setButtonsListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState(Bundle) called");
        outState.putInt(KEY_INDEX, mQuestionBank.getCurrentIndex());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    private void setButtonsListeners() {
        mTrueButton.setOnClickListener(event -> {
            if (mQuestionBank.isCurrentQuestionAnswerTrue()) {
                Toast.makeText(this, R.string.good_answer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton.setOnClickListener(event -> {
            if (mQuestionBank.isCurrentQuestionAnswerTrue() == false) {
                Toast.makeText(this, R.string.good_answer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            }
        });

        mNextButton.setOnClickListener(event -> {

            mQuestionBank.next();
            setQuestionText();

        });

        mCheatButton.setOnClickListener(event -> {
            Log.d(TAG, "Cheat button clicked");
            Intent intent = new Intent(this, CheatActivity.class);
            intent.putExtra(CheatActivity.ANSWER_EXTRA, mQuestionBank.isCurrentQuestionAnswerTrue());
            // startActivity(intent);
            mGetContent.launch(intent);
        });
    }

    private void setQuestionText() {
        mQuestionTextView.setText(mQuestionBank.getCurrentQuestionTextResId());
    }
}