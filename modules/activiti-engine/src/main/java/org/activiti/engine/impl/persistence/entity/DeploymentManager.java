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

package org.activiti.engine.impl.persistence.entity;

import java.util.List;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;


/**
 * @author Tom Baeyens
 */
public class DeploymentManager extends AbstractManager {
  
  public void insertDeployment(DeploymentEntity deployment) {
    getDbSqlSession().insert(deployment);
    
    for (ResourceEntity resource : deployment.getResources().values()) {
      resource.setDeploymentId(deployment.getId());
      getResourceManager().insertResource(resource);
    }
    
    Context
      .getProcessEngineConfiguration()
      .getDeploymentCache()
      .deploy(deployment);
  }
  
  public void deleteDeployment(String deploymentId, boolean cascade) {
    if (cascade) {
      List<ProcessDefinition> processDefinitions = getDbSqlSession()
        .createProcessDefinitionQuery()
        .deploymentId(deploymentId)
        .list();

      for (ProcessDefinition processDefinition: processDefinitions) {
        String processDefinitionId = processDefinition.getId();
        
        getProcessInstanceManager()
          .deleteProcessInstancesByProcessDefinition(processDefinitionId, "deleted deployment", cascade);

        Context
          .getProcessEngineConfiguration()
          .getDeploymentCache()
          .removeProcessDefinition(processDefinitionId);
      }
    }
    
    getProcessDefinitionManager()
      .deleteProcessDefinitionsByDeploymentId(deploymentId);
    
    getResourceManager()
      .deleteResourcesByDeploymentId(deploymentId);
    
    getDbSqlSession().delete("deleteDeployment", deploymentId);
  }


  public DeploymentEntity findLatestDeploymentByName(String deploymentName) {
    List<?> list = getDbSqlSession().selectList("selectDeploymentsByName", deploymentName, new Page(0, 1));
    if (list!=null && !list.isEmpty()) {
      return (DeploymentEntity) list.get(0);
    }
    return null;
  }
  
  public DeploymentEntity findDeploymentById(String deploymentId) {
    return (DeploymentEntity) getDbSqlSession().selectOne("selectDeploymentById", deploymentId);
  }
  
  public long findDeploymentCountByQueryCriteria(DeploymentQueryImpl deploymentQuery) {
    return (Long) getDbSqlSession().selectOne("selectDeploymentCountByQueryCriteria", deploymentQuery);
  }

  @SuppressWarnings("unchecked")
  public List<Deployment> findDeploymentsByQueryCriteria(DeploymentQueryImpl deploymentQuery, Page page) {
    final String query = "selectDeploymentsByQueryCriteria";
    return getDbSqlSession().selectList(query, deploymentQuery, page);
  }
  
  @SuppressWarnings("unchecked")
  public List<String> getDeploymentResourceNames(String deploymentId) {
    return getDbSqlSession().getSqlSession().selectList("selectResourceNamesByDeploymentId", deploymentId);
  }

  public void close() {
  }

  public void flush() {
  }
}
