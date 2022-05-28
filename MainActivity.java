package com.salmanqadri.justjava2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView quantity_text_view;
    TextView order_summary_text_view;
    Button order_button;
    CheckBox whipped_cream_checkbox;
    CheckBox chocolate_checkbox;
    EditText name_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity_text_view = findViewById(R.id.quantity_text_view);
        order_summary_text_view = findViewById(R.id.order_summary_text_view);
        whipped_cream_checkbox = findViewById(R.id.whipped_cream_checkbox);
        chocolate_checkbox = findViewById(R.id.chocolate_checkbox);
        order_button = findViewById(R.id.order_button);
        name_editText = findViewById(R.id.name_editText);
    }

    int quantity = 1;

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more then 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less then 1 coffees", Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * @param view
     */
    public void submitOrder(View view) {
        // EditText
        String showEditText = name_editText.getText().toString();
        // Figure out if the user wants whipped cream tipping
        boolean hasWhippedCream = whipped_cream_checkbox.isChecked();
        // Figure out if the user wants whipped cream tipping
        boolean hasChocolate = chocolate_checkbox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        // Display the message of the order
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, showEditText);
        displayMessage(priceMessage);
    }

    /**
     * Calculate the price of the order
     *
     * @param addWhippedCream is weather or not the user wants whipped cream tipping
     * @param addChocolate    is weather or not the user wants chocolate tipping
     * @return total price
     */
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of the 1 cup of coffee
        int basePrice = 5;
        // Add $1 if the user wants whipped cream
        if (addWhippedCream) {
            basePrice += 1;
            // Add $2 if the user wants chocolate
        }
        if (addChocolate)
            basePrice += 2;
        // Calculating the total order price by multiplying by quantity
        return quantity * basePrice;
    }

    public String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price + " \nThank You";
        return priceMessage;
    }

    public void displayQuantity(int numberOfCoffees) {
        quantity_text_view.setText("" + numberOfCoffees);
    }

    public void displayMessage(String message) {
        order_summary_text_view.setText(message);
    }

}
