package com.solvd.insurance.interfaces;

import com.solvd.insurance.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnderwriteBusiness implements IUnderwrite{
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void description() {
        logger.info("this is a Business policy that we are underwriting via Interface!");
    }

    @Override
    public void policy(int policyNum) {
        logger.info("the policy number needs to be underwritten is: " + policyNum);
    }

    @Override
    public void startPolicyCheck(int policyNum, double cost) {
        if(cost > 6.25){
            logger.info("the Business policy cost check was successful, we are proceeding!");
        }else{
            logger.warn("Incorrect policy binding. you need to cancel this policy!");
        }
        logger.info("The check was done on this Business policy, number listed as: " + policyNum + " and the cost is: $" + cost);
    }


}
