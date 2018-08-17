package gloom.mwohlbach.gloomhavendecks;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> deck;
    private ArrayList<Integer> discardPile = new ArrayList<>();
    private Integer blessCounter = 0;
    private Integer curseCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDeck();
    }

    public void clickDeck(View view){
        int chosenCard = deck.remove(0);

        if(chosenCard == R.drawable.attack_mod_bless){
            blessCounter--;
            TextView textView = findViewById(R.id.blessCounter);
            textView.setText(blessCounter.toString());
        }
        else if(chosenCard == R.drawable.attack_mod_curse){
            curseCounter--;
            TextView textView = findViewById(R.id.curseCounter);
            textView.setText(curseCounter.toString());
        }
        else if(chosenCard == R.drawable.attack_mod_2x || chosenCard == R.drawable.attack_mod_null){
            Button shuffleButton = findViewById(R.id.shuffleButton);
            shuffleButton.setBackgroundColor(Color.RED);
        }
        updateDiscardPile(chosenCard);

    }

    public void updateDiscardPile(int chosenCard){
        discardPile.add(chosenCard);
        LinearLayout scrollView = findViewById(R.id.scrollboi);
        scrollView.removeAllViews();
        for(int i = discardPile.size()-1;i>=0;i--){
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param.setMargins(5, 10, 5, 10);
            iv.setLayoutParams(param);
            iv.setImageResource(discardPile.get(i));
            iv.setAdjustViewBounds(true);
            scrollView.addView(iv);
        }
    }

    public void clickShuffle(View view){
        setupDeck();
        //clear the previous cards scroll bar
        LinearLayout scrollView = findViewById(R.id.scrollboi);
        scrollView.removeAllViews();
        discardPile.clear();
        Button shuffleButton = findViewById(R.id.shuffleButton);
        shuffleButton.setTextColor(Color.BLACK);
    }

    public void clickBless(View view){
        deck.add(R.drawable.attack_mod_bless);
        Collections.shuffle(deck);
        blessCounter++;
        TextView textView = findViewById(R.id.blessCounter);
        textView.setText(blessCounter.toString());
    }

    public void clickCurse(View view){
        deck.add(R.drawable.attack_mod_curse);
        Collections.shuffle(deck);
        curseCounter++;
        TextView textView = findViewById(R.id.curseCounter);
        textView.setText(curseCounter.toString());
    }

    public void clickUsedDeck(View view){
//        LinearLayout scrollView = findViewById(R.id.scrollboi);
//
//        ImageView iv = new ImageView(this);
//        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
//                , LinearLayout.LayoutParams.WRAP_CONTENT);
//        param.setMargins(0, 0, 10, 0);
//        iv.setLayoutParams(param);
//        iv.setImageResource(R.drawable.attack_mod_plus_1);
//        iv.setAdjustViewBounds(true);
//        System.out.println("iv.getX() = " + iv.getX());
//        System.out.println("iv.getY() = " + iv.getY());
//        scrollView.addView(iv);
//
//        ImageView ivv = new ImageView(this);
//        LinearLayout.LayoutParams paramalso = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
//                , LinearLayout.LayoutParams.WRAP_CONTENT);
//        paramalso.setMargins(0, 0, 50, 0);
//        paramalso.leftMargin = 100;
//        ivv.setLayoutParams(paramalso);
//        ivv.setImageResource(R.drawable.attack_mod_minus_1);
//        ivv.setAdjustViewBounds(true);
//        System.out.println("iv.getX() = " + ivv.getX());
//        System.out.println("iv.getY() = " + ivv.getY());
//        scrollView.addView(ivv);



    }

    public void setupDeck(){
        deck = new ArrayList<>();
        deck.add(R.drawable.attack_mod_0); deck.add(R.drawable.attack_mod_0); deck.add(R.drawable.attack_mod_0); deck.add(R.drawable.attack_mod_0); deck.add(R.drawable.attack_mod_0); deck.add(R.drawable.attack_mod_0);
        deck.add(R.drawable.attack_mod_plus_1); deck.add(R.drawable.attack_mod_plus_1); deck.add(R.drawable.attack_mod_plus_1); deck.add(R.drawable.attack_mod_plus_1); deck.add(R.drawable.attack_mod_plus_1);
        deck.add(R.drawable.attack_mod_minus_1); deck.add(R.drawable.attack_mod_minus_1); deck.add(R.drawable.attack_mod_minus_1); deck.add(R.drawable.attack_mod_minus_1); deck.add(R.drawable.attack_mod_minus_1);
        deck.add(R.drawable.attack_mod_plus_2);
        deck.add(R.drawable.attack_mod_minus_2);
        deck.add(R.drawable.attack_mod_2x);
        deck.add(R.drawable.attack_mod_null);
        for(int i=0;i<blessCounter;i++){
            deck.add(R.drawable.attack_mod_bless);
        }
        for(int i=0;i<curseCounter;i++){
            deck.add(R.drawable.attack_mod_curse);
        }
        Collections.shuffle(deck);
    }


}
