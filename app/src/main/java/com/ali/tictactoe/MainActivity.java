package com.ali.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    String message;
    boolean isGameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningState = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void playAgain(View view){
        LinearLayout playAgain = findViewById(R.id.playAgainLayout);
        playAgain.setVisibility(View.INVISIBLE);
        isGameActive = true;
        activePlayer = 0;
        for (int i =0; i < gameState.length; i++) {
            gameState[i]=2;
        }
        ;
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    public void getIn(View view) throws InterruptedException {

        ImageView counter =  (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());

        if (gameState[tag]==2 && isGameActive) {

            counter.setTranslationY(-1000f);

            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                gameState[tag]=1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                gameState[tag]=0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
            for(int[] winState : winningState){
                if(gameState[winState[0]]== gameState[winState[1]] &&
                        gameState[winState[1]] == gameState[winState[2]] &&gameState[winState[0]]!=2){
                    if(activePlayer==0){
                        message= "Red Won";
                    }else{
                        message= "Yellow Won";
                    }
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(message);
                    LinearLayout playAgain = findViewById(R.id.playAgainLayout);
                    playAgain.setVisibility(View.VISIBLE);
                    isGameActive = false;
                }else{
                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                        if(counterState == 2 ){
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's Draw!");
                        LinearLayout playAgain = findViewById(R.id.playAgainLayout);
                        playAgain.setVisibility(View.VISIBLE);
                        isGameActive = false;
                    }
                }
            }

        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
