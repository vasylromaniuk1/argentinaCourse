package com.solvd.insurance.InsuranceCompanies;

import com.solvd.insurance.interfaces.IUnderwrite;
import java.util.Random;

public class Farmers extends AbstractCompany {

  private static final String FARMERS_POLICY_PREFIX = "FARMERS-";
  private static final double FARMERS_POLICY_COST_ADDITION = 15;

  public Farmers (){

  }

  @Override
  public String getQuoteDetails(AbstractInsuranceType insuranceType) {

    double policyCost  = generatePolicyCost();
    String policyFinalNum  =  FARMERS_POLICY_PREFIX + insuranceType.getPolicyNum();
    String policyType = getPolicyType(insuranceType);

    String quoteDetails =
        String.format("You policy Summary for < %s > \n Your rate: $%,.2f/mo \n Your policy number: %s", policyType, policyCost, policyFinalNum);
    return quoteDetails;
  }

  public double generatePolicyCost(){
    return super.generatePolicyCost()+FARMERS_POLICY_COST_ADDITION;
  }

  @Override
  public String getCompanyName() {
    return "\n============== \n Welcome to Farmers \n============== \n";
  }

  @Override
  public String getPolicyType(AbstractInsuranceType insuranceType) {
    return insuranceType.getPolicyType();
  }

}
