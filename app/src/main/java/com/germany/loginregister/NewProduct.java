package com.germany.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NewProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String spinnerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
//            spinner.setPrompt("Selecciona");
        }
        // ... End of onCreate code ...
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int
            i, long l) {
        spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void displayToast(String spinnerLabel) {

        Toast.makeText(this,spinnerLabel , Toast.LENGTH_LONG).show();

    }

    public void btnCrearProducto(View view) {

    }

    public void btnCancel(View view) {
    }
}