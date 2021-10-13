package dev.simoni.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView _pizzaImageView;

    private final List<Integer> _ingList = new ArrayList<Integer>();
    private final List<Topping> _toppingList = new ArrayList<Topping>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _pizzaImageView = findViewById(R.id.pizza_image);

        _ingList.add(R.drawable.red_sauce);

        buildPizza();

        _toppingList.add(new Topping(R.id.cheeseBox, R.drawable.cheese));
        _toppingList.add(new Topping(R.id.pepperoniBox, R.drawable.pepperoni));
        _toppingList.add(new Topping(R.id.beefBox, R.drawable.beef));
        _toppingList.add(new Topping(R.id.mushroomBox, R.drawable.mushrooms));

        ((RadioGroup)findViewById(R.id.sauce_group)).setOnCheckedChangeListener((RadioGroup group, int checkId) -> {
            int resId;

            if (checkId == R.id.white_sauce_button) {
                resId = R.drawable.white_sauce;
            } else {
                resId = R.drawable.red_sauce;
            }

            if (_ingList.size() > 0)
                _ingList.set(0, resId); // We want sauce to be under everything so it needs to be index 0
            else
                _ingList.add(resId);

            buildPizza();
        });

        for(int i = 0; i < _toppingList.size(); i++) {
            int finalI = i;
            ((CheckBox)findViewById(_toppingList.get(finalI).getToppingControl()))
                    .setOnCheckedChangeListener((CompoundButton view, boolean isChecked) -> {
                if (isChecked) {
                    addIngredient(_toppingList.get(finalI).getToppingDrawable());
                } else {
                    removeIngredient(_toppingList.get(finalI).getToppingDrawable());
                }
            });
        }
    }

    private void addIngredient(int drawableId) {
        if (_ingList.contains(drawableId)) return;

        _ingList.add(drawableId);

        buildPizza();
    }

    private void removeIngredient(int drawableId) {
        do {
            _ingList.remove((Integer)drawableId);
        } while (_ingList.contains(drawableId));

        buildPizza();
    }

    private void buildPizza() {
        Drawable[] layers = new Drawable[_ingList.size() + 1];
        layers[0] = ContextCompat.getDrawable(getBaseContext(), R.drawable.blank_pizza);

        for (int i = 0; i < _ingList.size(); i++) {
            layers[i + 1] = ContextCompat.getDrawable(getBaseContext(), _ingList.get(i));
        }

        LayerDrawable layerDrawable = new LayerDrawable(layers);
        _pizzaImageView.setImageDrawable(layerDrawable);
    }
}