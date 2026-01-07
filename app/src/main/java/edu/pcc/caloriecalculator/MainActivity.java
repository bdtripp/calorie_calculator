package edu.pcc.caloriecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_WEIGHT = "com.murach.caloriesburnedcalculator.WEIGHT";
    public final static String EXTRA_MET = "com.murach.caloriesburnedcalculator.MET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View buttoon) {
        TextView weight = (TextView) findViewById(R.id.weightAmount);
        TextView metValue = (TextView) findViewById(R.id.metValue);

        String weightString = weight.getText().toString();
        String metString = metValue.getText().toString();

        try {
            float f = Float.parseFloat(weightString);
        } catch (NumberFormatException e){
            Toast.makeText(this, R.string.missingWeight, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            float f = Float.parseFloat(metString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.missingMET, Toast.LENGTH_LONG).show();
            return;
        }

        if (Float.parseFloat(weightString) > 1400f) {
            Toast.makeText(this, R.string.invalidWeight, Toast.LENGTH_LONG).show();
            return;
        }

        if ((Float.parseFloat(metString) < 0.9f) | (Float.parseFloat(metString) > 23f)) {
            Toast.makeText(this, R.string.invalidMET, Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, OutputActivity.class);

        intent.putExtra(EXTRA_WEIGHT, Float.parseFloat(weightString));
        intent.putExtra(EXTRA_MET, Float.parseFloat(metString));

        startActivity(intent);
    }
}
