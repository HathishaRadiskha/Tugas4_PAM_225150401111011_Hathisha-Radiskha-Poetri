package com.example.aplikasimahasiswa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private List<Data> dataList;
    private EditText nameEditText, codeEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        nameEditText = findViewById(R.id.nameInput);
        codeEditText = findViewById(R.id.codeInput);
        saveButton = findViewById(R.id.button);

        dataList = new ArrayList<>();
        dataAdapter = new DataAdapter(dataList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String code = codeEditText.getText().toString();

            if (!name.isEmpty() && !code.isEmpty()) {
                Log.d("MainActivity", "Data ditambahkan: Name = " + name + ", Code = " + code);
                dataList.add(new Data(name, code));
                dataAdapter.notifyItemInserted(dataList.size() - 1);
                checkIfEmpty();

                nameEditText.setText("");
                codeEditText.setText("");
            }

        });
    }

    private void checkIfEmpty() {
        if (dataList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}