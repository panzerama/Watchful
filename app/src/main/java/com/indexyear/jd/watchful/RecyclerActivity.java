package com.indexyear.jd.watchful;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    String[] leftStrings = {"j6BSQMKwsM", "yR632nnnve", "zx99PkfjOg", "cfR2AurdYl", "W3W9T7G8xX",
            "Hzti5z9LCl", "MFQYmrc7Hw", "irj7yDcL3Y", "Jl9sz5KS1m", "rG1ogatsso", "JRNlkwtRbO",
            "HIOriyeXTf", "4L9oZoPgMM", "bMNh5bXnF9", "DnWMWSwYDc", "G6X9R5dnmR", "Kun6oAyEfh",
            "Rxd7oq9cfe", "P7qlydlkI4", "CXIxJIcacv", "cDIXKNv5My", "QIxwGOO5Oc", "aIabanZOhY",
            "91oXWjZW62", "2LV4v9NGJi", "flLK0XVfl9", "SiHghOamvO", "EQX1jByuG1", "2lbte5Ag0V",
            "B3j5SV9s41", "GeORaOyIzN", "TKsIGofqeq", "19UmmLwHKd", "ULcN0M4sqs", "ewm8TOUQXr",
            "s6f5K5dQvE", "BQkMqwGXLn", "tBdPI27Aei", "sbudLVTv7k", "4mItZuw2dh", "LKVHjEHzuu",
            "iiKq0jbO1J", "egk6LnXMvp", "qW8ztsfcNB", "s52MlwSG44", "QH9iL2VfSL", "IyhNufjWLQ",
            "DBWxhqCMwp", "zedta6goyO", "opjgAM51Tq"};

    String[] rightStrings = {"yeDTw3dlhV", "llX0sqGKvu", "K3YHF1Qsrt", "SO0VcbpFnv", "kTeQwwLWob",
            "m7IVFA5EY6", "8WfIZjL78K", "sXScgdwlKc", "k8FpdCiXir", "cHWBlsH5Ok", "I639TWTr3g",
            "SUggTdtJCL", "eMsSt4Da8L", "9dIrq7FMqw", "huAH4X6fbI", "DZ5EjrN9dM", "CM17cs1fIc",
            "k2jRKOoziF", "4meF8MgWkC", "9dHhfDq3zF", "Pdg3yPDvuK", "JnbvVxJcdj", "DDYIbxD4X5",
            "vtjr9XFi68", "Rt9r4DOYpz", "qlFydfzjSr", "k4g6zutD0T", "9QwBLumQdi", "Ec6bLC0xUz",
            "uYSmeQYAq1", "28vRLWXU8H", "wF7qz3jfxQ", "2IWTicKjBJ", "I0s1OTZPmH", "PESol9xBH0",
            "gGMSLmlFVE", "EfNbUsEwpJ", "6kc75d4F2V", "FRJVBWN0Be", "TJc3mF1L9g", "2wshrnfTIj",
            "zQLWFVEWqb", "I07iGs6mvS", "KUEE0l8BSh", "9gREf6zw24", "0nwyJErSAO", "2E1zCN9bHZ",
            "TViUd2qeO2", "TpOiolQr3x", "atV6sAOerR"};

}