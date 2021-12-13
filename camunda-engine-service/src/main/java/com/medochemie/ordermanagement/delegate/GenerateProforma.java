package com.medochemie.ordermanagement.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.logging.Logger;

@Named("GenerateProforma")
public class GenerateProforma implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Generate Proforma");


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("The proforma order was '" + execution.getVariable("action") + "'" + " by " +  execution.getVariable("approver")  + "." );
        System.out.println("Inside GenerateProforma class!");
    }
}
