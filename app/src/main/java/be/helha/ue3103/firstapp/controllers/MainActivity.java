package be.helha.ue3103.firstapp.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.helha.ue3103.firstapp.R;
import be.helha.ue3103.firstapp.models.QuestionsBank;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private QuestionsBank mQuestionBank = new QuestionsBank();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton = this.findViewById(R.id.true_button);
        mFalseButton = this.findViewById(R.id.false_button);
        mNextButton = this.findViewById(R.id.next_button);
        mQuestionTextView = this.findViewById(R.id.question_text_view);
        setQuestionText();
        setButtonsListeners();
    }

    private void setButtonsListeners() {
        mTrueButton.setOnClickListener(event -> {
            if (mQuestionBank.isCurrentQuestionAnswerTrue()) {
                Toast.makeText(this, R.string.good_answer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            }
//            questionText.setText("Cliqué !");
//            Log.d("MonQuiz/MainActivity", "Clic sur le bouton Vrai");
        });
        mFalseButton.setOnClickListener(event -> {
            if (mQuestionBank.isCurrentQuestionAnswerTrue() == false) {
                Toast.makeText(this, R.string.good_answer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            }
        });

        mNextButton.setOnClickListener(event -> {
//            mCurrentIndex++;
//            if (mCurrentIndex == mQuestionBank.length) {
//                mCurrentIndex = 0;
//            }
//            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            mQuestionBank.next();
            setQuestionText();

        });
    }

    private void setQuestionText() {
        mQuestionTextView.setText(mQuestionBank.getCurrentQuestionTextResId());
    }
}