package com.iam_vip.colin.emptyview4;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.OperationApplicationException;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        TypedArray actionbarSizeTypedArray = this.obtainStyledAttributes(new int[]{
                android.R.attr.actionBarSize
        });
        int h = actionbarSizeTypedArray.getDimensionPixelOffset(0, 0);
        tvContactInfo.setHeight(h);

        tvContactInfo.setTextSize(h / 4);

        this.resetContactInfo();
    }

    private void resetContactInfo() {
        Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int count = cur.getCount();
        cur.close();
        tvContactInfo.setText("Count contact is " + count);
    }


    private void initComponents() {
        tvContactInfo = (TextView) this.findViewById(R.id.tv_contact_info);

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
                //
                Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                //
                ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>(cur.getCount());
                //
                while (cur.moveToNext()) {
                    long _ID = cur.getLong(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    ops.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,_ID)).build());
                }
                //
                if (ops.size()>0) {
                    try {
                        this.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                        this.showSuccessClean();
                    } catch (RemoteException | OperationApplicationException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    this.showSuccessClean();
                }
                cur.close();
                //
                this.resetContactInfo();
                break;
        }
    }

    private void showSuccessClean() {
        Toast.makeText(this, "Success to clean contacts!", Toast.LENGTH_LONG).show();
    }


}
