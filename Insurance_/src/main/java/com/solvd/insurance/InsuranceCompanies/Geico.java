package com.solvd.insurance.InsuranceCompanies;

public class Geico extends AbstractCompany {

  static final String GEICO_POLICY_PREFIX = "GEICO-";
  static final double GEICO_POLICY_COST_ADDITION = 9;

  public Geico(){

  }

  @Override
  public String getQuoteDetails(AbstractInsuranceType insuranceType) {

    double policyCost  = generatePolicyCost();
    String policyFinalNum  =  GEICO_POLICY_PREFIX + insuranceType.getPolicyNum();
    String policyType = getPolicyType(insuranceType);

    String quoteDetails =
        String.format("You policy Summary for < %s > \n Your rate: $%,.2f/mo \n Your policy number: %s", policyType, policyCost, policyFinalNum);
    return quoteDetails;
  }

  public double generatePolicyCost(){
    return super.generatePolicyCost()+GEICO_POLICY_COST_ADDITION;
  }

  @Override
  public String getCompanyName() {
    return "\n============== \n Welcome to Geico \n============== \n";
  }

  @Override
  public String getPolicyType(AbstractInsuranceType insuranceType) {
    return insuranceType.getPolicyType();
  }


}
