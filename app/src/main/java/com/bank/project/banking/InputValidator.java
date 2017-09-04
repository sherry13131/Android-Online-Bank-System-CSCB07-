package com.bank.project.banking;

import android.widget.EditText;

/**
 * Created by Khansa on 29/07/2017.
 */

public class InputValidator {

  /**
   * Checks if given EditText is empty.
   * @param editText view to check if empty
   * @return true if empty, false otherwise
   */
  public static boolean isEmpty(EditText editText) {
    return editText.getText().toString().trim().length() == 0;
  }

}
