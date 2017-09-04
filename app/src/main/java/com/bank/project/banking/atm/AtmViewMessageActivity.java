package com.bank.project.banking.atm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bank.database.android.DriverHelper;
import com.bank.project.banking.R;

public class AtmViewMessageActivity extends AppCompatActivity {

  TextView messageView;
  Intent intent;
  int messageId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_atm_view_message);

    // get message id to view
    intent = getIntent();
    messageId = intent.getIntExtra("messageId", -1);

    // create a new instance of the database
    Context context = getApplicationContext();
    DriverHelper db = new DriverHelper(context);
    // get message
    String message = db.getSpecificMessage(messageId);

    // set message
    messageView = (TextView) findViewById(R.id.message_view);
    messageView.setText(message);

    // add to intent that message was viewed
    Intent intentResult = new Intent();
    intentResult.putExtra("messageId", messageId);
    setResult(RESULT_OK, intentResult);
  }
}
