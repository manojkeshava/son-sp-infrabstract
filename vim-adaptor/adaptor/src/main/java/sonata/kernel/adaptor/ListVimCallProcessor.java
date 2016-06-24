package sonata.kernel.adaptor;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import sonata.kernel.adaptor.commons.VimResources;
import sonata.kernel.adaptor.messaging.ServicePlatformMessage;
import sonata.kernel.adaptor.wrapper.ComputeWrapper;
import sonata.kernel.adaptor.wrapper.ResourceUtilisation;
import sonata.kernel.adaptor.wrapper.WrapperBay;

import java.util.ArrayList;
import java.util.Observable;

public class ListVimCallProcessor extends AbstractCallProcessor {

  public ListVimCallProcessor(ServicePlatformMessage message, String sid, AdaptorMux mux) {
    super(message, sid, mux);
  }

  @Override
  public void update(Observable obs, Object arg) {
    // This call does not need to be updated by any observable (wrapper).
  }

  @Override
  public boolean process(ServicePlatformMessage message) {

    ArrayList<String> vimList = WrapperBay.getInstance().getComputeWrapperList();
    ArrayList<VimResources> resList = new ArrayList<VimResources>();
    for (String vimUuid : vimList) {
      ComputeWrapper wr = WrapperBay.getInstance().getComputeWrapper(vimUuid);

      ResourceUtilisation resource = wr.getResourceUtilisation();

      if (resource != null) {

        VimResources bodyElement = new VimResources();

        bodyElement.setVimUuid(vimUuid);
        bodyElement.setCoreTotal(resource.getTotCores());
        bodyElement.setCoreUsed(resource.getUsedCores());
        bodyElement.setMemoryTotal(resource.getTotMemory());
        bodyElement.setMemoryUsed(resource.getUsedMemory());
        resList.add(bodyElement);
      }
    }

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
    mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
    mapper.setSerializationInclusion(Include.NON_NULL);
    String body;
    try {
      body = mapper.writeValueAsString(resList);


      ServicePlatformMessage response = new ServicePlatformMessage(body, "application/x-yaml",
          this.getMessage().getReplyTo(), this.getSid(), null);

      this.getMux().enqueue(response);
      return true;
    } catch (JsonProcessingException e) {
      ServicePlatformMessage response =
          new ServicePlatformMessage("{\"status\":\"ERROR\",\"message\":\"Internal Server Error\"}",
              "application/json", this.getMessage().getReplyTo(), this.getSid(), null);
      this.getMux().enqueue(response);
      return false;
    }
  }

}