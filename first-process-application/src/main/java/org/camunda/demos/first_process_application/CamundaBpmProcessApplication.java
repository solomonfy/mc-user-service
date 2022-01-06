package org.camunda.demos.first_process_application;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "first-process-application";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {

    //create variables to start process

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("name", "John");
    variables.put("start_date", "2019-01-10T08:00:00");
    variables.put("end_date", "2019-01-10T18:00:00");
    variables.put("reason", "sick leave");

    //start process using process Id in BPMN diagram and setting variables using Map<String, Object>
    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);

  }

}
