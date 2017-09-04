package com.bank.project.banking.teller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bank.database.android.DriverHelper;
import com.bank.project.banking.MainActivity;
import com.bank.project.banking.R;

public class TellerMenuActivity extends AppCompatActivity {

  Intent intent;
  int tellerId = -1;
  int customerId = -1;
  TextView messageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teller_menu);

    // check if no current teller
    if (tellerId == -1) {
      // get teller id from intent
      intent = getIntent();
      tellerId = intent.getIntExtra("tellerId", tellerId);

      // create instance of db and get teller's name
      Context context = getApplicationContext();
      DriverHelper db = new DriverHelper(context);
      String name = db.getUserName(tellerId);

      // add a welcome message for teller
      messageView = (TextView) findViewById(R.id.teller_hello);
      messageView.setText("Welcome, " + name + ".");
    }

    // go to authenticate customer page
    Button authCustomer = (Button) findViewById(R.id.button_auth_cus);
    authCustomer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(TellerMenuActivity.this, TellerAuthCustomerActivity.class);
        // if there is a current customer, block from going to next page and pop up message
        if (customerId != -1) {
          // show message saying that teller terminal is being used
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.existing,
              Toast.LENGTH_SHORT);
          toast.show();

        } else {
          // else go to next page to get a customer id
          startActivityForResult(intent, 1);
        }
      }
    });

    // go to make new customer page
    Button makeCustomer = (Button) findViewById(R.id.button_make_cus);
    makeCustomer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(TellerMenuActivity.this, TellerMakeUserActivity.class));
      }
    });

    // go to make new account page
    Button makeAccount = (Button) findViewById(R.id.button_make_account);
    makeAccount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();

        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this, TellerMakeAccountActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to give interest page
    Button giveInterest = (Button) findViewById(R.id.button_give_interest);
    giveInterest.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this, TellerGiveInterestActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to make deposit page
    Button makeDeposit = (Button) findViewById(R.id.button_teller_deposit);
    makeDeposit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this, TellerDepositActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to make withdrawal page
    Button makeWithdrawal = (Button) findViewById(R.id.button_teller_withdraw);
    makeWithdrawal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this, TellerWithdrawActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to check balance page
    Button checkBalance = (Button) findViewById(R.id.button_teller_check_bal);
    checkBalance.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this,
              TellerCheckBalanceActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to account list page
    Button listAccount = (Button) findViewById(R.id.button_teller_list_acs);
    listAccount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this,
              TellerListAccountsActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to view total balance page
    Button viewTotalBalance = (Button) findViewById(R.id.button_total_bal);
    viewTotalBalance.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent
          Intent intent = new Intent(TellerMenuActivity.this,
              TellerViewTotalBalanceActivity.class);
          intent.putExtra("customerId", customerId);
          startActivity(intent);
        }
      }
    });

    // go to update info page
    Button updateInfo = (Button) findViewById(R.id.button_teller_update_userinfo);
    updateInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // if no current customer, block from going to next page and pop up message
        if (customerId == -1) {
          // can't go to next page because no customer
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.no_current_customer,
              Toast.LENGTH_SHORT);
          toast.show();
        } else {
          // else go to next page and add customer id to intent to update welcome message
          Intent intent = new Intent(TellerMenuActivity.this,
              TellerUpdateCustomerInfoActivity.class);
          intent.putExtra("customerId", customerId);
          startActivityForResult(intent, 2);
        }
      }
    });

    // when close customer session button is clicked, log out the current customer
    Button deauthCus = (Button) findViewById(R.id.button_deauth_cus);
    deauthCus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // set the customer id to -1
        customerId = -1;
        // show that customer session is closed
        Toast toast = Toast.makeText(getApplicationContext(),
            R.string.msg_deauthenticated,
            Toast.LENGTH_SHORT);
        toast.show();

        // get instance of db and get teller name
        Context context = getApplicationContext();
        DriverHelper db = new DriverHelper(context);
        String name = db.getUserName(tellerId);

        // set welcome message for teller again
        messageView = (TextView) findViewById(R.id.teller_hello);
        messageView.setText("Welcome, " + name + ".");
      }
    });

    // go to message page
    Button message = (Button) findViewById(R.id.button_message);
    message.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // add teller id and customer id to intent
        Intent intent = new Intent(TellerMenuActivity.this,
            TellerMainMessageActivity.class);
        intent.putExtra("tellerId", tellerId);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
      }
    });

    // when log out button is clicked
    Button logout = (Button) findViewById(R.id.button_teller_logout);
    logout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // log out customer and teller and go back to main login page
        customerId = -1;
        tellerId = -1;
        startActivity(new Intent(TellerMenuActivity.this, MainActivity.class));
      }
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // after authenticating customer
    if (requestCode == 1) {
      if (resultCode == RESULT_OK) {
        // set customer id if customer is authenticated
        customerId = data.getIntExtra("customerId", -1);

        // get instance of db and get customer name and address
        Context context = getApplicationContext();
        DriverHelper db = new DriverHelper(context);
        String customerName = db.getUserName(customerId);
        String customerAddress = db.getUserAddress(customerId);

        // set the welcome message for the customer
        messageView = (TextView) findViewById(R.id.teller_hello);
        messageView
            .setText("Current customer is " + customerName + " from " + customerAddress + ".");

        // show message showing customer is authenticated
        Toast toast = Toast.makeText(getApplicationContext(),
            R.string.customer_authenticated,
            Toast.LENGTH_SHORT);
        toast.show();
      }


    } else if (requestCode == 2) {
      // after updating customer information
      if (resultCode == RESULT_OK) {
        // get instance of db and customer's name and address
        Context context = getApplicationContext();
        DriverHelper db = new DriverHelper(context);
        String customerName = db.getUserName(customerId);
        String customerAddress = db.getUserAddress(customerId);

        // update welcome address for customer
        messageView = (TextView) findViewById(R.id.teller_hello);
        messageView
            .setText("Current customer is " + customerName + " from " + customerAddress + ".");
      }
    }
  }

  // if back key is pressed
  @Override
  public void onBackPressed() {
    // log out teller and customer
    tellerId = -1;
    customerId = -1;
    startActivity(new Intent(TellerMenuActivity.this, MainActivity.class));
  }
}
