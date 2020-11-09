package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SuccessDto implements ResponsePayload {
    String message;
    ResourceType resourceType;
    String id;
    EventType event;

    @Override
    public Object toModel() {
        return null;
    }

    public enum EventType {
        CREATED,
        DELETED,
        UPDATED
    }
}
