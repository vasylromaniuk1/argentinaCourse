package com.solvd.insurance.interfaces;

public interface IUnderwrite {
    public void description();
    public void policy(int policyNum);
    public void startPolicyCheck(int policyNum, double cost);

}
