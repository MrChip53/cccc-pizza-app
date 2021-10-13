package dev.simoni.pizzaapp;

public class Topping {
    private final int _toppingControl; // ID to the checkbox for this topping
    private final int _toppingDrawable; // ID to the drawable for this topping

    public Topping(int toppingControl, int toppingDrawable) {
        this._toppingControl = toppingControl;
        this._toppingDrawable = toppingDrawable;
    }

    public int getToppingControl() {
        return _toppingControl;
    }

    public int getToppingDrawable() {
        return _toppingDrawable;
    }
}
