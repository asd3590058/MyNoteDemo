package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mydemo.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.btn_algorithm)
	Button btnAlgorithm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}


	@OnClick(R.id.btn_algorithm)
	public void onViewClicked() {
		startActivity(new Intent(MainActivity.this,AlgorithmActivity.class));
	}
}
