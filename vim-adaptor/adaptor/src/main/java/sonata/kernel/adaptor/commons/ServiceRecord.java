/**
 * @author Dario Valocchi (Ph.D.)
 * @mail d.valocchi@ucl.ac.uk
 * 
 *       Copyright 2016 [Dario Valocchi]
 * 
 *       Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *       except in compliance with the License. You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *       Unless required by applicable law or agreed to in writing, software distributed under the
 *       License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *       either express or implied. See the License for the specific language governing permissions
 *       and limitations under the License.
 * 
 */

package sonata.kernel.adaptor.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceRecord {

  @JsonProperty("descriptor_reference")
  private String descriptorReference;
  private Status status;
  @JsonProperty("instance_uuid")
  private String instanceUuid;



  public Status getStatus() {
    return status;
  }


  public void setStatus(Status status) {
    this.status = status;
  }

  public String getInstanceUuid() {
    return instanceUuid;
  }

  public void setInstanceUuid(String instanceUuid) {
    this.instanceUuid = instanceUuid;
  }


  public String getDescriptorReference() {
    return descriptorReference;
  }


  public void setDescriptorReference(String descriptorReference) {
    this.descriptorReference = descriptorReference;
  }



}
