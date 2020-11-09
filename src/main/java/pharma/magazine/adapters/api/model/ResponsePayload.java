package pharma.magazine.adapters.api.model;

public interface ResponsePayload<T> {
    T toModel();
}
