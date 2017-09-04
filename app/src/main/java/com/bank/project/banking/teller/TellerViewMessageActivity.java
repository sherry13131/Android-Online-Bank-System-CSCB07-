package com.bank.project.banking.teller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bank.database.android.DriverHelper;
import com.bank.project.banking.R;

public class TellerViewMessageActivity extends AppCompatActivity {

  TextView messageView;
  Intent intent;
  int messageId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teller_view_message);

    // get the message to read from intent
    intent = getIntent();
    messageId = intent.getIntExtra("messageId", -1);

    // create a new instance of the database
    Context context = getApplicationContext();
    DriverHelper db = new DriverHelper(context);
    // get message
    String message = db.getSpecificMessage(messageId);

    // get text view and set the message
    messageView = (TextView) findViewById(R.id.message_view);
    messageView.setText(message);

    // message is read so put this into the intent so it gets updated
    Intent intentResult = new Intent();
    intentResult.putExtra("messageId", messageId);
    setResult(RESULT_OK, intentResult);
  }
}
