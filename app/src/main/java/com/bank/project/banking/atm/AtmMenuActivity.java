package com.bank.project.banking.atm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import com.bank.database.android.DriverHelper;
import com.bank.project.banking.MainActivity;
import com.bank.project.banking.R;

public class AtmMenuActivity extends AppCompatActivity {

  Intent intent;
  int customerId = -1;
  TextView messageView;
  TextView addressView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_atm_menu);

    // no customer at the moment
    if (customerId == -1) {
      // get customer id from intent
      intent = getIntent();
      customerId = intent.getIntExtra("customerId", customerId);

      // get db instance and customer's info
      Context context = getApplicationContext();
      DriverHelper db = new DriverHelper(context);
      String name = db.getUserName(customerId);
      String address = db.getUserAddress(customerId);

      // set welcome message for customer
      messageView = (TextView) findViewById(R.id.hello_customer);
      messageView.setText("Welcome, " + name + ".");
      addressView = (TextView) findViewById(R.id.cus_name_address);
      addressView.setText(address + ".");
    }

    // go to list accounts page
    Button listAccounts = (Button) findViewById(R.id.button_atm_list_ac);
    listAccounts.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add customer id to intent
        Intent intent = new Intent(AtmMenuActivity.this, AtmListAccountsActivity.class);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
      }
    });

    // go to deposit page
    Button deposit = (Button) findViewById(R.id.button_atm_deposit);
    deposit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add customer id to intent
        Intent intent = new Intent(AtmMenuActivity.this, AtmDepositActivity.class);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
      }
    });

    // go to withdraw page
    Button withdraw = (Button) findViewById(R.id.button_atm_withdraw);
    withdraw.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add customer id to intent
        Intent intent = new Intent(AtmMenuActivity.this, AtmWithdrawActivity.class);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
      }
    });

    // go to check balance page
    Button checkBalance = (Button) findViewById(R.id.button_atm_check_bal);
    checkBalance.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add customer id to intent
        Intent intent = new Intent(AtmMenuActivity.this, AtmCheckBalanceActivity.class);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
      }
    });

    // go to view message page
    Button viewMessage = (Button) findViewById(R.id.button_message);
    viewMessage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add customer id to intent
        Intent intent = new Intent(AtmMenuActivity.this, AtmMainMessageActivity.class);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
      }
    });

    // when logout button is clicked
    Button logout = (Button) findViewById(R.id.button_atm_logout);
    logout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // log out customer and back to main login page
        customerId = -1;
        startActivity(new Intent(AtmMenuActivity.this, MainActivity.class));
      }
    });
  }

  // if back key is pressed
  @Override
  public void onBackPressed() {
    // log out customer
    customerId = -1;
    startActivity(new Intent(AtmMenuActivity.this, MainActivity.class));
  }
}
