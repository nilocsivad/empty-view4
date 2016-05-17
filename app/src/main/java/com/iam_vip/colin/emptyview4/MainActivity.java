package com.iam_vip.colin.emptyview4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initComponents();

    }

    private void initComponents() {
        TextView cleanTV = (TextView) this.findViewById(R.id.btn_clean_contact);
        cleanTV.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clean_contact:
                Toast.makeText(this, "" + System.currentTimeMillis(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
