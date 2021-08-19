package com.solvd.insurance.enums;

public enum InsurancePolicyOptions {

  CAR (1),
  BUSINESS(2),
  BOAT(3),
  LIFE(4),
  PETS(5),
  HOME(6),
  RENTERS(7),
  BOND(8),
  NONE(9),
  PRINT_ALL(0);


  private final int optionCode;

  private InsurancePolicyOptions(int optionCode) {
    this.optionCode = optionCode;
  }

  public static InsurancePolicyOptions getName(int value){
    InsurancePolicyOptions[] options = InsurancePolicyOptions.values();
    InsurancePolicyOptions result = null;
    for(InsurancePolicyOptions option : options) {

      if (option.optionCode==value){
        return option;
      }
  }
    return null;
  }

}
