package pharma.magazine.adapters.api.service;

public interface IDtoConverter<T,S> {
    S converter(T t);
}
