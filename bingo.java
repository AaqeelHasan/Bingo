package com.example.rajfe.bingosp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Single3 extends AppCompatActivity {

    Button button, playAgain;
    Button buttons[] = new Button[25];

    ImageView home;

    TextView textViewS3;

    boolean exitStatus;
    boolean b[] = new boolean[12];
    boolean ob[] = new boolean[12];

    ArrayList<Integer> al, generateValues;
    HashMap<Integer,Integer> oppV;
    HashMap<Integer,Boolean> oppR;

    Toast toast;

    int check, oppCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single3);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textViewS3 = findViewById(R.id.textViewS3);
        playAgain = findViewById(R.id.buttonS3PA);
        home = findViewById(R.id.home);

        al = new ArrayList<>();
        generateValues = new ArrayList<>();
        oppV = new HashMap<>();
        oppR = new HashMap<>();
        check = 0;
        oppCheck = 0;
        exitStatus = true;

        Bundle bundle = getIntent().getExtras();
        int[] iArr = bundle.getIntArray("IntArray");

        for(int i=1;i<26;i++) {
            if(i<10) {
                button = findViewById(getResources().getIdentifier("buttonS30" + i, "id",
                        this.getPackageName()));
                buttons[i-1] = findViewById(getResources().getIdentifier("buttonS30" + i, "id",
                        this.getPackageName()));
            } else {
                button = findViewById(getResources().getIdentifier("buttonS3" + i, "id",
                        this.getPackageName()));
                buttons[i-1] = findViewById(getResources().getIdentifier("buttonS3" + i, "id",
                        this.getPackageName()));
            }
            button.setText(Integer.toString(iArr[i-1]));
            button.setTextColor(getColor(R.color.white));

            generateValues.add(i);

            playAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Single3.this, Single2.class);
                    startActivity(intent);
                    finish();
                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        Collections.shuffle(generateValues);
        int m=1;
        for(int n:generateValues) {
            oppV.put(m,n);
            oppR.put(m,false);
            m++;
        }

        for(int j=0;j<12;j++) {
            b[j] = true;
            ob[j] = true;
        }

    }

    public void clickButton(View view) {
        textViewS3.setVisibility(View.INVISIBLE);
        int random;
        button = findViewById(view.getId());
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.grayborder));
//        button.setTextColor(getColor(R.color.black));
        button.setEnabled(false);
        al.add(Integer.parseInt(button.getText().toString()));
        oppR.put(Integer.parseInt(button.getText().toString()),true);
        checkForBingo();
        oppCheckForBingo();
        if(check>=5 || oppCheck>=5) {
            return;
        }
        while(true) {
            random = ThreadLocalRandom.current().nextInt(1, 25);
            if(!al.contains(random)) {
                break;
            }
        }
        al.add(random);
        oppR.put(random,true);
        for(int i=1;i<26;i++) {
            if(i<10) {
                button = findViewById(getResources().getIdentifier("buttonS30" + i, "id",
                        this.getPackageName()));
            } else {
                button = findViewById(getResources().getIdentifier("buttonS3" + i, "id",
                        this.getPackageName()));
            }
            if(Integer.parseInt(button.getText().toString()) == random) {
                button.setBackground(ContextCompat.getDrawable(this, R.drawable.grayborder));
//                button.setTextColor(getColor(R.color.black));
                button.setEnabled(false);
                break;
            }
        }
        if(toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(Single3.this, "Opponent: "+Integer.toString(random), Toast.LENGTH_SHORT);
        toast.show();
//        Toast.makeText(this, "Opponent: "+Integer.toString(random), Toast.LENGTH_SHORT).show();
        checkForBingo();
        oppCheckForBingo();
        if(check>=5 || oppCheck>=5) {
            return;
        }
//        Toast.makeText(this, Integer.toString(oppCheck), Toast.LENGTH_SHORT).show();
    }

    public void checkForBingo() {

        if(!buttons[0].isEnabled() && !buttons[1].isEnabled() && !buttons[2].isEnabled() && !buttons[3].isEnabled()
                && !buttons[4].isEnabled() && b[0]) {
            b[0] = false;
            check++;
            buttons[0].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[1].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[2].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[3].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[4].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[0].setTextColor(getColor(R.color.black));
            buttons[1].setTextColor(getColor(R.color.black));
            buttons[2].setTextColor(getColor(R.color.black));
            buttons[3].setTextColor(getColor(R.color.black));
            buttons[4].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[5].isEnabled() && !buttons[6].isEnabled() && !buttons[7].isEnabled() && !buttons[8].isEnabled()
                && !buttons[9].isEnabled() && b[1]) {
            b[1] = false;
            check++;
            buttons[5].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[6].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[7].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[8].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[9].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[5].setTextColor(getColor(R.color.black));
            buttons[6].setTextColor(getColor(R.color.black));
            buttons[7].setTextColor(getColor(R.color.black));
            buttons[8].setTextColor(getColor(R.color.black));
            buttons[9].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[10].isEnabled() && !buttons[11].isEnabled() && !buttons[12].isEnabled() && !buttons[13].isEnabled()
                && !buttons[14].isEnabled() && b[2]) {
            b[2] = false;
            check++;
            buttons[10].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[11].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[12].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[13].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[14].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[10].setTextColor(getColor(R.color.black));
            buttons[11].setTextColor(getColor(R.color.black));
            buttons[12].setTextColor(getColor(R.color.black));
            buttons[13].setTextColor(getColor(R.color.black));
            buttons[14].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[15].isEnabled() && !buttons[16].isEnabled() && !buttons[17].isEnabled() && !buttons[18].isEnabled()
                && !buttons[19].isEnabled() && b[3]) {
            b[3] = false;
            check++;
            buttons[15].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[16].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[17].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[18].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[19].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[15].setTextColor(getColor(R.color.black));
            buttons[16].setTextColor(getColor(R.color.black));
            buttons[17].setTextColor(getColor(R.color.black));
            buttons[18].setTextColor(getColor(R.color.black));
            buttons[19].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[20].isEnabled() && !buttons[21].isEnabled() && !buttons[22].isEnabled() && !buttons[23].isEnabled()
                && !buttons[24].isEnabled() && b[4]) {
            b[4] = false;
            check++;
            buttons[20].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[21].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[22].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[23].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[24].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[20].setTextColor(getColor(R.color.black));
            buttons[21].setTextColor(getColor(R.color.black));
            buttons[22].setTextColor(getColor(R.color.black));
            buttons[23].setTextColor(getColor(R.color.black));
            buttons[24].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[0].isEnabled() && !buttons[5].isEnabled() && !buttons[10].isEnabled() && !buttons[15].isEnabled()
                && !buttons[20].isEnabled() && b[5]) {
            b[5] = false;
            check++;
            buttons[0].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[5].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[10].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[15].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[20].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[0].setTextColor(getColor(R.color.black));
            buttons[5].setTextColor(getColor(R.color.black));
            buttons[10].setTextColor(getColor(R.color.black));
            buttons[15].setTextColor(getColor(R.color.black));
            buttons[20].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[1].isEnabled() && !buttons[6].isEnabled() && !buttons[11].isEnabled() && !buttons[16].isEnabled()
                && !buttons[21].isEnabled() && b[6]) {
            b[6] = false;
            check++;
            buttons[1].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[6].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[11].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[16].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[21].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[1].setTextColor(getColor(R.color.black));
            buttons[6].setTextColor(getColor(R.color.black));
            buttons[11].setTextColor(getColor(R.color.black));
            buttons[16].setTextColor(getColor(R.color.black));
            buttons[21].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[2].isEnabled() && !buttons[7].isEnabled() && !buttons[12].isEnabled() && !buttons[17].isEnabled()
                && !buttons[22].isEnabled() && b[7]) {
            b[7] = false;
            check++;
            buttons[2].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[7].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[12].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[17].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[22].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[2].setTextColor(getColor(R.color.black));
            buttons[7].setTextColor(getColor(R.color.black));
            buttons[12].setTextColor(getColor(R.color.black));
            buttons[17].setTextColor(getColor(R.color.black));
            buttons[22].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[3].isEnabled() && !buttons[8].isEnabled() && !buttons[13].isEnabled() && !buttons[18].isEnabled()
                && !buttons[23].isEnabled() && b[8]) {
            b[8] = false;
            check++;
            buttons[3].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[8].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[13].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[18].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[23].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[3].setTextColor(getColor(R.color.black));
            buttons[8].setTextColor(getColor(R.color.black));
            buttons[13].setTextColor(getColor(R.color.black));
            buttons[18].setTextColor(getColor(R.color.black));
            buttons[23].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[4].isEnabled() && !buttons[9].isEnabled() && !buttons[14].isEnabled() && !buttons[19].isEnabled()
                && !buttons[24].isEnabled() && b[9]) {
            b[9] = false;
            check++;
            buttons[4].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[9].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[14].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[19].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[24].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[4].setTextColor(getColor(R.color.black));
            buttons[9].setTextColor(getColor(R.color.black));
            buttons[14].setTextColor(getColor(R.color.black));
            buttons[19].setTextColor(getColor(R.color.black));
            buttons[24].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[0].isEnabled() && !buttons[6].isEnabled() && !buttons[12].isEnabled() && !buttons[18].isEnabled()
                && !buttons[24].isEnabled() && b[10]) {
            b[10] = false;
            check++;
            buttons[0].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[6].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[12].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[18].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[24].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[0].setTextColor(getColor(R.color.black));
            buttons[6].setTextColor(getColor(R.color.black));
            buttons[12].setTextColor(getColor(R.color.black));
            buttons[18].setTextColor(getColor(R.color.black));
            buttons[24].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
        if(!buttons[4].isEnabled() && !buttons[8].isEnabled() && !buttons[12].isEnabled() && !buttons[16].isEnabled()
                && !buttons[20].isEnabled() && b[11]) {
            b[11] = false;
            check++;
            buttons[4].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[8].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[12].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[16].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[20].setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            buttons[4].setTextColor(getColor(R.color.black));
            buttons[8].setTextColor(getColor(R.color.black));
            buttons[12].setTextColor(getColor(R.color.black));
            buttons[16].setTextColor(getColor(R.color.black));
            buttons[20].setTextColor(getColor(R.color.black));
            markCheck();
            if(check>=5) {
                return;
            }
        }
    }

    public void markCheck() {

        switch(check) {
            case 0: break;
            case 1: button = findViewById(R.id.buttonS3B);
                button.setVisibility(View.VISIBLE);
                break;
            case 2: button = findViewById(R.id.buttonS3I);
                button.setVisibility(View.VISIBLE);
                break;
            case 3: button = findViewById(R.id.buttonS3N);
                button.setVisibility(View.VISIBLE);
                break;
            case 4: button = findViewById(R.id.buttonS3G);
                button.setVisibility(View.VISIBLE);
                break;
            case 5: button = findViewById(R.id.buttonS3O);
                button.setVisibility(View.VISIBLE);
                stopGame();
                break;
        }

    }

    public void oppCheckForBingo() {

        if(oppR.get(oppV.get(1)) && oppR.get(oppV.get(2)) && oppR.get(oppV.get(3)) && oppR.get(oppV.get(4))
                && oppR.get(oppV.get(5)) && ob[0]) {
            ob[0] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(6)) && oppR.get(oppV.get(7)) && oppR.get(oppV.get(8)) && oppR.get(oppV.get(9))
                && oppR.get(oppV.get(10)) && ob[1]) {
            ob[1] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(11)) && oppR.get(oppV.get(12)) && oppR.get(oppV.get(13)) && oppR.get(oppV.get(14))
                && oppR.get(oppV.get(15)) && ob[2]) {
            ob[2] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(16)) && oppR.get(oppV.get(17)) && oppR.get(oppV.get(18)) && oppR.get(oppV.get(19))
                && oppR.get(oppV.get(20)) && ob[3]) {
            ob[3] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(21)) && oppR.get(oppV.get(22)) && oppR.get(oppV.get(23)) && oppR.get(oppV.get(24))
                && oppR.get(oppV.get(25)) && ob[4]) {
            ob[4] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(1)) && oppR.get(oppV.get(6)) && oppR.get(oppV.get(11)) && oppR.get(oppV.get(16))
                && oppR.get(oppV.get(21)) && ob[5]) {
            ob[5] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(2)) && oppR.get(oppV.get(7)) && oppR.get(oppV.get(12)) && oppR.get(oppV.get(17))
                && oppR.get(oppV.get(22)) && ob[6]) {
            ob[6] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(3)) && oppR.get(oppV.get(8)) && oppR.get(oppV.get(13)) && oppR.get(oppV.get(18))
                && oppR.get(oppV.get(23)) && ob[7]) {
            ob[7] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(4)) && oppR.get(oppV.get(9)) && oppR.get(oppV.get(14)) && oppR.get(oppV.get(19))
                && oppR.get(oppV.get(24)) && ob[8]) {
            ob[8] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(5)) && oppR.get(oppV.get(10)) && oppR.get(oppV.get(15)) && oppR.get(oppV.get(20))
                && oppR.get(oppV.get(25)) && ob[9]) {
            ob[9] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(1)) && oppR.get(oppV.get(7)) && oppR.get(oppV.get(13)) && oppR.get(oppV.get(19))
                && oppR.get(oppV.get(25)) && ob[10]) {
            ob[10] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }
        if(oppR.get(oppV.get(5)) && oppR.get(oppV.get(9)) && oppR.get(oppV.get(13)) && oppR.get(oppV.get(17))
                && oppR.get(oppV.get(21)) && ob[11]) {
            ob[11] = false;
            oppCheck++;
            if(oppCheck>=5) {
                stopGame();
                return;
            }
        }

    }

    public  void stopGame() {
        for(int i=1;i<26;i++) {
            if(i<10) {
                button = findViewById(getResources().getIdentifier("buttonS30" + i, "id",
                        this.getPackageName()));
            } else {
                button = findViewById(getResources().getIdentifier("buttonS3" + i, "id",
                        this.getPackageName()));
            }
            button.setEnabled(false);
        }
        if(check>oppCheck) {
            textViewS3.setText("You won");
        } else if(oppCheck>check) {
            textViewS3.setText("You lost");
        } else {
            textViewS3.setText("Draw");
        }
        textViewS3.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.VISIBLE);
        exitStatus = false;
//        Toast.makeText(this, Integer.toString(oppCheck), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(exitStatus) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you really want to leave in the middle?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Single3.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            Intent intent = new Intent(Single3.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void goBack() {
//        if(exitStatus) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Do you really want to leave in the middle?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Intent intent = new Intent(Single3.this, MainActivity.class);
//                            startActivity(intent);
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.dismiss();
//                        }
//                    });
//            builder.create().show();
//        } else {
//            Intent intent = new Intent(Single3.this, MainActivity.class);
//            startActivity(intent);
//        }
    }
}

