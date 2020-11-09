package pharma.magazine.adapters.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pharma.magazine.adapters.api.model.ListDto;
import pharma.magazine.adapters.api.model.ResourceType;
import pharma.magazine.adapters.api.model.ResponsePayload;
import pharma.magazine.adapters.api.model.SuccessDto;
import pharma.magazine.adapters.magazinedb.entity.PrintableId;
import pharma.magazine.adapters.magazinedb.entity.Staff;
import pharma.magazine.adapters.service.SessionService;
import pharma.magazine.domain.model.ICrud;
import pharma.magazine.domain.ports.service.ICrudService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Crud<T extends ResponsePayload<S>, S extends ICrud, R extends PrintableId> {

    protected abstract SessionService getSession();
    protected abstract ICrudService<S> getCrudService();
    protected abstract IDtoConverter<S, T> getDtoConverterService();

    protected ResponseEntity<ResponsePayload> createEntity(Class<T> type, T dto, Function<S, R> function) {
        S model = dto.toModel();
        Staff currentUser = getSession().getCurrentUser();
        S saved = getCrudService().create(model, currentUser.toModel());
        return createdEntity(type, function.apply(saved).printId());
    }

    protected ResponseEntity<ResponsePayload> updateEntity(Class<T> type, T dto, Function<S, R> function) {
        S model = dto.toModel();
        Staff currentUser = getSession().getCurrentUser();
        Optional<S> updatedOpt = getCrudService().update(model, currentUser.toModel());
        S updated = updatedOpt.orElseThrow(UpdateException::new);
        return updatedEntity(type, function.apply(updated).printId());
    }

    protected ResponseEntity<ResponsePayload> createdEntity(Class<T> type, String id) {
        return new ResponseEntity<>(SuccessDto.builder()
                .event(SuccessDto.EventType.CREATED)
                .resourceType(ResourceType.fromClass(type))
                .id(Optional.ofNullable(id).map(Object::toString).orElse(null))
                .message(type.getSimpleName() + " successfully created with id: " + id)
                .build(), HttpStatus.CREATED);
    }

    protected ResponseEntity<ResponsePayload> updatedEntity(Class<T> type, String id) {
        return new ResponseEntity<>(SuccessDto.builder()
                .event(SuccessDto.EventType.UPDATED)
                .resourceType(ResourceType.fromClass(type))
                .id(Optional.ofNullable(id).map(Object::toString).orElse(null))
                .message(type.getSimpleName() + " successfully updated with id: " + id)
                .build(), HttpStatus.OK);
    }

    protected ResponseEntity<?> getEntities() {
        List<S> all = getCrudService().findAll();
        long count = all.size();
        List<T> elements = all.stream()
                .map(s -> getDtoConverterService().converter(s)).collect(Collectors.toList());
        return ResponseEntity.ok(ListDto.<T>builder().total(count).content(elements).build());
    }

}
