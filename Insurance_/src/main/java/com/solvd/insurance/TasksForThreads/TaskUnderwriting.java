package com.solvd.insurance.TasksForThreads;

import com.solvd.insurance.InsuranceCompanies.AbstractCompany;
import com.solvd.insurance.InsuranceCompanies.AbstractInsuranceType;
import com.solvd.insurance.interfaces.IUnderwrite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskUnderwriting implements Runnable {


  private AbstractCompany company;
  private AbstractInsuranceType quoteType;
  private IUnderwrite underwrite;
  public static final Logger LOGGER = LogManager.getLogger(TaskUnderwriting.class);


  public TaskUnderwriting(AbstractCompany company, AbstractInsuranceType quoteType, IUnderwrite underwrite) {
    this.company=company;
    this.quoteType=quoteType;
    this.underwrite=underwrite;
  }


  @Override
  public void run() {
    LOGGER.info("= Executing underwriting thread =");
    company.performUnderwriting(quoteType, underwrite);
  }
}
