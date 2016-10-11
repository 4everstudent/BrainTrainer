package a4everstudent.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


// TODO: 05-10-2016 main screen :
// TODO: 05-10-2016 countdowntimer connected to TimeLeft
// TODO: 05-10-2016 variables correct answer, total questions
// TODO: 05-10-2016 onClick on buttons, correct or wrong?
// TODO: 05-10-2016 update function
// TODO: 05-10-2016 last screen: show score, show button
// TODO: 05-10-2016 playAgain function
// TODO: 07-10-2016 FutureFeatures: wrong and right sounds
// TODO: 07-10-2016 FF: settings - choose gameDuration
// TODO: 07-10-2016 FF: finish sound 

public class MainActivity extends AppCompatActivity {

    TextView timeLeftTextView;
    CountDownTimer countDownTimer;
    int gameDuration = 30;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;


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
        Button button1 = (Button) findViewById(R.id.firstChoiceButton);
        Button button2 = (Button) findViewById(R.id.secondChoiceButton);
        Button button3 = (Button) findViewById(R.id.thirdChoiceButton);
        Button button4 = (Button) findViewById(R.id.fourthChoiceButton);

        timeLeft(gameDuration);
        TextView questionTextView = (TextView) findViewById(R.id.questionView);

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        questionTextView.setText(Integer.toString(a)+ " + " +Integer.toString(b));

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
}
