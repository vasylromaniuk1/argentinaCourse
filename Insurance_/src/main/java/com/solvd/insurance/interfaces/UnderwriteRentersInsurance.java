package com.solvd.insurance.interfaces;

import static com.solvd.insurance.Main.LOGGER;

public class UnderwriteRentersInsurance implements IUnderwrite{

    @Override
    public void description() {
        LOGGER.warn("this is a Renters Policy policy that we are underwriting via Interface!");
    }

    @Override
    public void policy(int policyNum) {
        LOGGER.info("the policy number needs to be underwritten is: " + policyNum);
    }

    @Override
    public void startPolicyCheck(int policyNum, double cost) {
        if(cost > 14){
            LOGGER.info("the Renters Insurance policy cost check was successful, we are proceeding!");
        } else{
           LOGGER.warn("Incorrect policy binding. you need to cancel this policy!");
        }
        LOGGER.info("The check was done on this Renters policy, number listed as: " + policyNum + " and the cost is: $" + cost);
    }
}
