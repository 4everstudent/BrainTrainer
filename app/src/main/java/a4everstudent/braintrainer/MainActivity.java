package a4everstudent.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


// TODO: 05-10-2016 main screen :
// TODO: 05-10-2016 countdowntimer connected to TimeLeft
// TODO: 05-10-2016 last screen: show score, show button
// TODO: 05-10-2016 playAgain function
// TODO: 07-10-2016 FutureFeatures: wrong and right sounds
// TODO: 07-10-2016 FF: settings - choose gameDuration
// TODO: 07-10-2016 FF: finish sound 

public class MainActivity extends AppCompatActivity {

    TextView timeLeftTextView, feedback, correctTotal, questionTextView;
    CountDownTimer countDownTimer;
    int gameDuration = 30;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;
    int score = 0;
    int total = 0;
    Button button1, button2, button3, button4;

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        questionTextView.setText(Integer.toString(a)+ " + " +Integer.toString(b));
        answers = new ArrayList<Integer>();
        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for(int i =0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(4);
                }

                answers.add(incorrectAnswer);

            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }
    public void updateScore(){
        total++;
        correctTotal.setText(Integer.toString(score)+ "/"+ Integer.toString(total));
    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            feedback.setText("Correct!");
            score++;
        }
        else{
            feedback.setText("Wrong!");
        }
        updateScore();
        generateQuestion();
    }
    public void timeLeft(int gameDuration){



        countDownTimer = new CountDownTimer(gameDuration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                //update the timer text view
                timeLeftTextView.setText((int)millisUntilFinished /1000);
            }

            @Override
            public void onFinish() {
                timeLeftTextView.setText("00:00");

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeLeftTextView = (TextView) findViewById(R.id.timeLeftTextView);
        button1 = (Button) findViewById(R.id.firstChoiceButton);
        button2 = (Button) findViewById(R.id.secondChoiceButton);
        button3 = (Button) findViewById(R.id.thirdChoiceButton);
        button4 = (Button) findViewById(R.id.fourthChoiceButton);

        timeLeft(gameDuration);
        questionTextView = (TextView) findViewById(R.id.questionView);
        feedback = (TextView) findViewById(R.id.feedbackTextView);
        correctTotal = (TextView) findViewById(R.id.correctTotalAnswersView);
        generateQuestion();

    }
}
