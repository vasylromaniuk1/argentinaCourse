package com.solvd.insurance.InsuranceCompanies;

public class TrippleA extends AbstractCompany {

  static final String TRIPLEA_POLICY_PREFIX = "TRIPLEA-";
  static final double TRIPLEA_POLICY_COST_ADDITION = 20;


  @Override
  public String getQuoteDetails(AbstractInsuranceType insuranceType) {
    double policyCost  = generatePolicyCost();
    String policyFinalNum  =  TRIPLEA_POLICY_PREFIX + insuranceType.getPolicyNum();
    String policyType = getPolicyType(insuranceType);

    String quoteDetails =
        String.format("You policy Summary for < %s > \n Your rate: $%,.2f/mo \n Your policy number: %s", policyType, policyCost, policyFinalNum);
    return quoteDetails;
  }

  @Override
  public String getCompanyName() {
    return "Triple A";
  }

  @Override
  public String getPolicyType(AbstractInsuranceType insuranceType) {
    return insuranceType.getPolicyType();
  }

  public double generatePolicyCost(){
    return super.generatePolicyCost() + TRIPLEA_POLICY_COST_ADDITION;

  }

}
