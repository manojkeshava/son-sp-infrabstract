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

package sonata.kernel.adaptor.commons.heat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeatServer {

  @JsonProperty("server_name")
  private String serverName;
  @JsonProperty("server_id")
  private String serverId;

  private InstanceFlavor flavor;

  public String getServerName() {
    return serverName;
  }

  public String getServerId() {
    return serverId;
  }

  public InstanceFlavor getFlavor() {
    return flavor;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  public void setServerId(String serverId) {
    this.serverId = serverId;
  }

  public void setFlavor(InstanceFlavor flavor) {
    this.flavor = flavor;
  }

}