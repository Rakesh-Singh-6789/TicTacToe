package com.example.rakeshsingh.a7connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 1:red 2:green 0:empty

    int[] gameState={0,0,0,0,0,0,0,0,0};
    int[][] winningSlots={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int token=2,clickCount=0;
    String winner="";
    boolean status=false;


    public void PlayAgain(View view){
        for(int i=0;i<gameState.length;i++){
            gameState[i]=0;
        }
        token=2;
        clickCount=0;
        status=false;
        Button playAgain=(Button)findViewById(R.id.playAgainButton);
        TextView textMessage=(TextView)findViewById(R.id.textView2);
        textMessage.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        GridLayout myGridView=(GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i<myGridView.getChildCount(); i++) {
            ImageView child = (ImageView) myGridView.getChildAt(i);
            //child.setVisibility(View.INVISIBLE);
            child.setImageDrawable(null);
        }
    }
    public void dropIn(View view){

        ImageView counter=(ImageView) view;
        int tag=Integer.parseInt(counter.getTag().toString());
        if(status){
            Toast.makeText(this, winner+" has already won, try again!", Toast.LENGTH_SHORT).show();
        }else {
            if (gameState[tag] == 0) {
                clickCount++;
                counter.setTranslationY(-1500);
                if (token == 1) {
                    counter.setImageResource(R.drawable.green);
                    token = 2;
                } else {
                    counter.setImageResource(R.drawable.red);
                    token = 1;
                }

                counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
                gameState[tag] = token;

                for (int[] winningPosition : winningSlots) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 0) {
                        if (token == 2) {
                            winner = "Green ";
                        } else {
                            winner = "Red ";
                        }
                        Toast.makeText(this, winner+"has won!", Toast.LENGTH_SHORT).show();
                        status = true;

                        String message=winner+" has won!";
                        Button playAgain=(Button)findViewById(R.id.playAgainButton);
                        TextView textMessage=(TextView)findViewById(R.id.textView2);
                        textMessage.setText(message);
                        textMessage.setVisibility(View.VISIBLE);
                        //Button playAgain=(Button)findViewById(R.id.playAgainButton);
                        playAgain.setVisibility(View.VISIBLE);

                    }
                }
            } else {
                Toast.makeText(this, "it is already occupied!", Toast.LENGTH_SHORT).show();
            }
        }
        if(clickCount==9&&!status){
            Button pA=(Button)findViewById(R.id.playAgainButton);
            Toast.makeText(this, "No body woN,play again!", Toast.LENGTH_SHORT).show();
            pA.setVisibility(View.VISIBLE);
            clickCount=0;
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
