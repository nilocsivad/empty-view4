package com.iam_vip.colin.emptyview4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContactInfo, tvCleanContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initComponents();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }




    private void initComponents() {
        tvContactInfo = (TextView)this.findViewById(R.id.tv_contact_info);

        tvCleanContact = (TextView) this.findViewById(R.id.tv_clean_contact);
        tvCleanContact.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clean_contact:
                break;
        }
    }
}
