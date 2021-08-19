package com.solvd.insurance.InsuranceCompanies;

import com.solvd.insurance.customer.Person;
import com.solvd.insurance.interfaces.IUnderwrite;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

abstract public class AbstractCompany {

  public abstract String getQuoteDetails(AbstractInsuranceType insuranceType);
  public abstract String getCompanyName();
  public abstract String getPolicyType(AbstractInsuranceType insuranceType);


  public int generatePolicyNumber(){
    Random random = new Random();
    return random.nextInt(1000);
  }

  public double generatePolicyCost(){
    //some baseRate
    return 10;
  }

  public void performUnderwriting(AbstractInsuranceType quoteType, IUnderwrite underwrite) {
    underwrite.description();
    underwrite.startPolicyCheck(quoteType.getPolicyNum(),quoteType.getCost());
  }

  public String getPersonalProfileInfo(Person person) {
    String personSummary = "Insurance profile is being generated for \n Name: "+person.getName()+"\n Age:"+person.getAge()+"\n Address: "+person.getAddress()+"\n";
    return personSummary;
  }

}
