package com.bank.project.banking.teller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bank.database.android.DriverHelper;
import com.bank.project.banking.InputValidator;
import com.bank.project.banking.R;

import java.math.BigDecimal;
import java.util.List;

public class TellerCheckBalanceActivity extends AppCompatActivity {

  EditText accountIdView;
  TextView messageView;
  Intent intent;
  int customerId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teller_check_balance);

    // get customer id
    intent = getIntent();
    customerId = intent.getIntExtra("customerId", -1);

    // when user presses button
    Button check = (Button) findViewById(R.id.button_check);
    check.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // get views
        accountIdView = (EditText) findViewById(R.id.account_id);
        messageView = (TextView) findViewById(R.id.message);

        // check if input is empty
        if (InputValidator.isEmpty(accountIdView)) {
          // show message to note leave blank
          messageView.setText(R.string.null_info);

        } else {
          // get the account id
          int accountId = Integer.parseInt(accountIdView.getText().toString());

          // create a new instance of the database
          Context context = getApplicationContext();
          DriverHelper db = new DriverHelper(context);

          // get account ids for user
          List<Integer> accountIds = db.getAccountIdsList(customerId);

          boolean hasAccess = false;

          // check if accountid passed in is inside user's list of accounts
          for (Integer i : accountIds) {
            if (accountId == i) {
              hasAccess = true;
            }
          }

          if (hasAccess) {
            // customer has access so show balance
            BigDecimal balance = db.getBalance(accountId);
            messageView.setText(getResources().getString(R.string.existed_bal_msg) +
                balance.setScale(2, BigDecimal.ROUND_HALF_UP));

          } else {
            // customer doesn't have access to account
            messageView.setText(R.string.not_existed_bal_msg);
          }
        }
      }
    });
  }
}
