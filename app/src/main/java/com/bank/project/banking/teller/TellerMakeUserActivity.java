package com.bank.project.banking.teller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bank.database.android.DriverHelper;
import com.bank.generics.Roles;
import com.bank.project.banking.InputValidator;
import com.bank.project.banking.R;
import java.util.EnumMap;

public class TellerMakeUserActivity extends AppCompatActivity {

  EditText nameView;
  EditText ageView;
  EditText addressView;
  EditText passwordView;
  TextView messageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teller_make_user);

    // when create button is pressed
    Button create = (Button) findViewById(R.id.button_create_customer);
    create.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // get views
        nameView = (EditText) findViewById(R.id.cus_name);
        ageView = (EditText) findViewById(R.id.cus_age);
        addressView = (EditText) findViewById(R.id.cus_address);
        passwordView = (EditText) findViewById(R.id.cus_password);
        messageView = (TextView) findViewById(R.id.message);

        // check if any are empty
        if (InputValidator.isEmpty(nameView) || InputValidator.isEmpty(ageView)
            || InputValidator.isEmpty(addressView) || InputValidator.isEmpty(passwordView)) {
          // if they are then show message
          Toast toast = Toast.makeText(getApplicationContext(),
              R.string.null_info,
              Toast.LENGTH_SHORT);
          toast.show();

        } else {
          // get user info
          String name = nameView.getText().toString();
          int age = Integer.parseInt(ageView.getText().toString());
          String address = addressView.getText().toString();
          String password = passwordView.getText().toString();

          // get instance of database
          Context context = getApplicationContext();
          DriverHelper db = new DriverHelper(context);

          // get customer role id
          EnumMap<Roles, Integer> roles = db.getRolesEnumMap();
          int customerRoleId = roles.get(Roles.CUSTOMER);

          // check if address is too long
          boolean invalidAddress = false;
          if (address.length() > 100) {
            invalidAddress = true;
          }

          if (invalidAddress) {
            // show message saying address is too long
            Toast toast = Toast.makeText(getApplicationContext(),
                R.string.address_long,
                Toast.LENGTH_SHORT);
            toast.show();

          } else {
            // insert the new user
            long inserted = db.insertNewUser(name, age, address, customerRoleId, password);

            // if successfully inserted
            if (inserted != -1) {
              // show message showing created and new customer id
              Toast toast = Toast.makeText(getApplicationContext(),
                  R.string.customer_created,
                  Toast.LENGTH_SHORT);
              toast.show();
              messageView
                  .setText(getResources().getString(R.string.new_customer_id) + " " + inserted);

            } else {
              // something went wrong in db and customer cannot be created
              Toast toast = Toast.makeText(getApplicationContext(),
                  R.string.customer_create_failed,
                  Toast.LENGTH_SHORT);
              toast.show();

            }
          }
        }
      }
    });
  }
}
