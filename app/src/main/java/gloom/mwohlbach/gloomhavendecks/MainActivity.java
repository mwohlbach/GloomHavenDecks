package gloom.mwohlbach.gloomhavendecks;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDeck();

        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"1", "2", "three"};
        ArrayList<String> ree = new ArrayList<>();
        ree.add("boo");
        ree.add("bear");

        ArrayList<ImageView> skre = new ArrayList<>();
        ImageView r = new ImageView(this);
        ImageView rr = new ImageView(this);
        r.setImageResource(R.drawable.icon_bless);
        rr.setImageResource(R.drawable.icon_curse);
        skre.add(r);
        skre.add(rr);

        ArrayAdapter<ImageView> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, skre);
        dropdown.setAdapter(adapter);

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
        ImageButton shuffleButton = findViewById(R.id.shuffleButton);
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

    public void clickCurse(View view){
        deck.add(R.drawable.attack_mod_curse);
        Collections.shuffle(deck);
        curseCounter++;
        TextView textView = findViewById(R.id.curseCounter);
        textView.setText(curseCounter.toString());
        updateDeckCount();
    }

    public void updateDeckCount(){
        TextView deckCount = findViewById(R.id.deckCount);
        deckCount.setText(String.valueOf(deck.size()));
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


}
