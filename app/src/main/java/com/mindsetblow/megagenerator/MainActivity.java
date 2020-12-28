package com.mindsetblow.megagenerator;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/*
* Author: Diego Roberto
* many thanks to SimmpleRandomOrgLib author: Charles Eakins
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private int quantidadeNumeros;
    private int min = 1;
    private int max = 60;
    private SimpleRandomOrgLib srol = new SimpleRandomOrgLib();
    private ArrayList ticket = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mViewHolder.editText = findViewById(R.id.text_result);
        this.mViewHolder.spinner = findViewById(R.id.spinner_number_select);
        this.mViewHolder.buttonSortear = findViewById(R.id.button_sortear);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_quantidade_numeros, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        this.mViewHolder.spinner.setAdapter(adapter);
        this.mViewHolder.spinner.setOnItemSelectedListener(this);

        this.mViewHolder.buttonSortear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        clearField();
        if (view.getId() == R.id.button_sortear){
            this.mViewHolder.spinner.getOnItemSelectedListener();

            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try {
                    ticket = srol.randomNumberBaseTenInt(getQuantidaNumeros(), min, max);

                    //ticket = srol.randomNumberLottery(getQuantidaNumeros());
                    //implementar randomNumberLottery posteriormente!!

                    this.mViewHolder.editText.setText(ticket.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    String erro = "erro: " + e.toString();
                    Toast.makeText(this, erro, Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    private void clearField(){
        this.mViewHolder.editText.setText("");
    }

    public int getQuantidaNumeros() {
        return quantidadeNumeros;
    }

    public void setQuantidaNumeros(int selected) {
        this.quantidadeNumeros = selected;
    }

    private static class ViewHolder{
        EditText editText;
        Button buttonSortear;
        Spinner spinner;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String quantidadeTemp = (parent.getItemAtPosition(position).toString()); //ja recebe um Int, mas tem que converter por whatever the fuck reason...
        setQuantidaNumeros(Integer.parseInt(quantidadeTemp));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //nothing
        //sempre vai ter pelo menos a seleção de 6 números no spinner
    }
}