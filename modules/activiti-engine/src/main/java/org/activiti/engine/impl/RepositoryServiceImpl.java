/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.impl;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cmd.ActivateProcessDefinitionCmd;
import org.activiti.engine.impl.cmd.ActivateProcessInstanceCmd;
import org.activiti.engine.impl.cmd.DeleteDeploymentCmd;
import org.activiti.engine.impl.cmd.DeployCmd;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.cmd.GetDeploymentResourceCmd;
import org.activiti.engine.impl.cmd.GetDeploymentResourceNamesCmd;
import org.activiti.engine.impl.cmd.SuspendProcessDefinitionCmd;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.repository.DeploymentBuilderImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinitionQuery;


/**
 * @author Tom Baeyens
 */
public class RepositoryServiceImpl extends ServiceImpl implements RepositoryService {

  public DeploymentBuilder createDeployment() {
    return new DeploymentBuilderImpl(this);
  }

  public Deployment deploy(DeploymentBuilderImpl deploymentBuilder) {
    return commandExecutor.execute(new DeployCmd<Deployment>(deploymentBuilder));
  }

  public void deleteDeployment(String deploymentId) {
    commandExecutor.execute(new DeleteDeploymentCmd(deploymentId, false));
  }

  public void deleteDeploymentCascade(String deploymentId) {
    commandExecutor.execute(new DeleteDeploymentCmd(deploymentId, true));
  }
  
  public void deleteDeployment(String deploymentId, boolean cascade) {
    commandExecutor.execute(new DeleteDeploymentCmd(deploymentId, cascade));
  }

  public ProcessDefinitionQuery createProcessDefinitionQuery() {
    return new ProcessDefinitionQueryImpl(commandExecutor);
  }

  @SuppressWarnings("unchecked")
  public List<String> getDeploymentResourceNames(String deploymentId) {
    return commandExecutor.execute(new GetDeploymentResourceNamesCmd(deploymentId));
  }

  public InputStream getResourceAsStream(String deploymentId, String resourceName) {
    return commandExecutor.execute(new GetDeploymentResourceCmd(deploymentId, resourceName));
  }

  public DeploymentQuery createDeploymentQuery() {
    return new DeploymentQueryImpl(commandExecutor);
  }

  public ReadOnlyProcessDefinition getDeployedProcessDefinition(String processDefinitionId) {
    return commandExecutor.execute(new GetDeploymentProcessDefinitionCmd(processDefinitionId));
  }

  public void suspendProcessDefinitionById(String processDefinitionId) {
    commandExecutor.execute(new SuspendProcessDefinitionCmd(processDefinitionId, null));
  }

  public void suspendProcessDefinitionByKey(String processDefinitionKey) {
    commandExecutor.execute(new SuspendProcessDefinitionCmd(null, processDefinitionKey));
  }

  public void activateProcessDefinitionById(String processDefinitionId) {
    commandExecutor.execute(new ActivateProcessDefinitionCmd(processDefinitionId, null));
  }

  public void activateProcessDefinitionByKey(String processDefinitionKey) {
    commandExecutor.execute(new ActivateProcessDefinitionCmd(null, processDefinitionKey));
  }
  
  
}

