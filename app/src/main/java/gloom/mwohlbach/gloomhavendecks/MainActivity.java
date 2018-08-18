package gloom.mwohlbach.gloomhavendecks;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> deck;
    private ArrayList<Integer> discardPile = new ArrayList<>();
    private Integer blessCounter = 0;
    private Integer curseCounter = 0;

    private ArrayList<Integer> deckPlayer;
    private ArrayList<Integer> discardPilePlayer = new ArrayList<>();
    private Integer blessCounterPlayer = 0;
    private Integer curseCounterPlayer = 0;
    private Integer minusCounterPlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDeck();
        setupDeckPlayer();
    }

    public void clickDeck(View view){
        if(deck.isEmpty()){
            return;
        }
        int chosenCard = deck.remove(0);
        updateDeckCount();

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
            ImageButton shuffleButton = findViewById(R.id.shuffleButton);
            shuffleButton.setBackgroundColor(Color.RED);
        }
        updateDiscardPile(chosenCard,R.id.scrollboi,discardPile);

    }

    public void clickDeckPlayer(View view){
        if(deckPlayer.isEmpty()){
            return;
        }
        int chosenCard = deckPlayer.remove(0);
        updateDeckCountPlayer();

        if(chosenCard == R.drawable.attack_mod_bless){
            blessCounterPlayer--;
            TextView textView = findViewById(R.id.blessCounterPlayer);
            textView.setText(blessCounterPlayer.toString());
        }
        else if(chosenCard == R.drawable.attack_mod_curse){
            curseCounterPlayer--;
            TextView textView = findViewById(R.id.curseCounterPlayer);
            textView.setText(curseCounterPlayer.toString());
        }
        else if(chosenCard == R.drawable.attack_mod_2x || chosenCard == R.drawable.attack_mod_null){
            ImageButton shuffleButton = findViewById(R.id.shuffleButtonPlayer);
            shuffleButton.setBackgroundColor(Color.RED);
        }
        updateDiscardPile(chosenCard,R.id.scrollboiplayer,discardPilePlayer);

    }

    public void updateDiscardPile(int chosenCard, int scrollId, ArrayList<Integer> discard){
        discard.add(chosenCard);
        LinearLayout scrollView = findViewById(scrollId);
        scrollView.removeAllViews();
        for(int i = discard.size()-1;i>=0;i--){
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            param.setMargins(5, 10, 5, 10);
            iv.setLayoutParams(param);
            iv.setImageResource(discard.get(i));
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
        ImageButton shuffleButton = findViewById(R.id.shuffleButton);
        view.setBackgroundColor(Color.TRANSPARENT);
    }

    public void clickShufflePlayer(View view){
        setupDeckPlayer();
        //clear the previous cards scroll bar
        LinearLayout scrollView = findViewById(R.id.scrollboiplayer);
        scrollView.removeAllViews();
        discardPilePlayer.clear();
        ImageButton shuffleButton = findViewById(R.id.shuffleButtonPlayer);
        view.setBackgroundColor(Color.TRANSPARENT);
    }

    public void clickBless(View view){
        deck.add(R.drawable.attack_mod_bless);
        Collections.shuffle(deck);
        blessCounter++;
        TextView textView = findViewById(R.id.blessCounter);
        textView.setText(blessCounter.toString());
        updateDeckCount();
    }

    public void clickBlessPlayer(View view){
        deckPlayer.add(R.drawable.attack_mod_bless);
        Collections.shuffle(deckPlayer);
        blessCounterPlayer++;
        TextView textView = findViewById(R.id.blessCounterPlayer);
        textView.setText(blessCounterPlayer.toString());
        updateDeckCountPlayer();
    }

    public void clickCurse(View view){
        deck.add(R.drawable.attack_mod_curse);
        Collections.shuffle(deck);
        curseCounter++;
        TextView textView = findViewById(R.id.curseCounter);
        textView.setText(curseCounter.toString());
        updateDeckCount();
    }

    public void clickCursePlayer(View view){
        deckPlayer.add(R.drawable.attack_mod_curse);
        Collections.shuffle(deckPlayer);
        curseCounterPlayer++;
        TextView textView = findViewById(R.id.curseCounterPlayer);
        textView.setText(curseCounterPlayer.toString());
        updateDeckCountPlayer();
    }

    public void clickMinusAddPlayer(View view){
        deckPlayer.add(R.drawable.attack_mod_minus_1_special);
        Collections.shuffle(deckPlayer);
        minusCounterPlayer++;
        TextView textView = findViewById(R.id.minusCounterPlayer);
        textView.setText(minusCounterPlayer.toString());
        updateDeckCountPlayer();
    }

    public void clickPlusAddPlayer(View view){
        if(minusCounterPlayer==0){
            return;
        }
        for(int i = 0;i<deckPlayer.size();i++){
            int cardId = deckPlayer.get(i);
            if(cardId == R.drawable.attack_mod_minus_1_special){
                deckPlayer.remove(i);
                break;
            }
        }
        Collections.shuffle(deckPlayer);
        minusCounterPlayer--;
        TextView textView = findViewById(R.id.minusCounterPlayer);
        textView.setText(minusCounterPlayer.toString());
        updateDeckCountPlayer();
    }

    public void updateDeckCount(){
        TextView deckCount = findViewById(R.id.deckCount);
        deckCount.setText(String.valueOf(deck.size()));
    }

    public void updateDeckCountPlayer(){
        TextView deckCount = findViewById(R.id.deckCountPlayer);
        deckCount.setText(String.valueOf(deckPlayer.size()));
    }

    public void clickDropdown(View view){
        Spinner spinner = findViewById(R.id.spinner);
        String booga = spinner.getSelectedItem().toString();
        System.out.println("Watup fam: " + booga);
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
        updateDeckCount();
    }

    public void setupDeckPlayer(){
        deckPlayer = new ArrayList<>();
        deckPlayer.add(R.drawable.attack_mod_0); deckPlayer.add(R.drawable.attack_mod_0); deckPlayer.add(R.drawable.attack_mod_0); deckPlayer.add(R.drawable.attack_mod_0); deckPlayer.add(R.drawable.attack_mod_0); deckPlayer.add(R.drawable.attack_mod_0);
        deckPlayer.add(R.drawable.attack_mod_plus_1); deckPlayer.add(R.drawable.attack_mod_plus_1); deckPlayer.add(R.drawable.attack_mod_plus_1); deckPlayer.add(R.drawable.attack_mod_plus_1); deckPlayer.add(R.drawable.attack_mod_plus_1);
        deckPlayer.add(R.drawable.attack_mod_minus_1); deckPlayer.add(R.drawable.attack_mod_minus_1); deckPlayer.add(R.drawable.attack_mod_minus_1); deckPlayer.add(R.drawable.attack_mod_minus_1); deckPlayer.add(R.drawable.attack_mod_minus_1);
        deckPlayer.add(R.drawable.attack_mod_plus_2);
        deckPlayer.add(R.drawable.attack_mod_minus_2);
        deckPlayer.add(R.drawable.attack_mod_2x);
        deckPlayer.add(R.drawable.attack_mod_null);
        for(int i=0;i<blessCounterPlayer;i++){
            deckPlayer.add(R.drawable.attack_mod_bless);
        }
        for(int i=0;i<curseCounterPlayer;i++){
            deckPlayer.add(R.drawable.attack_mod_curse);
        }
        for(int i=0;i<minusCounterPlayer;i++){
            deckPlayer.add(R.drawable.attack_mod_minus_1_special);
        }
        Collections.shuffle(deckPlayer);
        updateDeckCountPlayer();
    }


}
