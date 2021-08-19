package com.solvd.insurance.TasksForThreads;

import com.solvd.insurance.InsuranceCompanies.AbstractCompany;
import com.solvd.insurance.InsuranceCompanies.AbstractInsuranceType;
import com.solvd.insurance.interfaces.IUnderwrite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskGetCompanyName implements Runnable {


  private AbstractCompany company;
  public static final Logger LOGGER = LogManager.getLogger(TaskGetCompanyName.class);


  public TaskGetCompanyName(AbstractCompany company) {
    this.company=company;
  }


  @Override
  public void run() {
    LOGGER.info("= Executing get company name thread =");
    LOGGER.info(company.getCompanyName());
  }
}
