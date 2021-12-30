package es.udc.asi.restexample.web;

import java.util.List;

import es.udc.asi.restexample.model.domain.User;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.asi.restexample.web.exceptions.IdAndBodyNotMatchingOnUpdateException;
import es.udc.asi.restexample.web.exceptions.RequestBodyNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import es.udc.asi.restexample.model.service.UserService;
import es.udc.asi.restexample.model.service.dto.UserDTOPublic;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserResource {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserDTOPublic> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public UserDTOPublic findOne(@PathVariable Long id) throws NotFoundException {
    return userService.findById(id);
  }

  @PutMapping("/{id}")
  public UserDTOPrivate update(@PathVariable Long id, @RequestBody @Valid UserDTOPrivate user, Errors errors) throws RequestBodyNotValidException,
    IdAndBodyNotMatchingOnUpdateException, OperationNotAllowed, NotFoundException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    if (id != user.getId()) {
      throw new IdAndBodyNotMatchingOnUpdateException(User.class);
    }
    return userService.update(user);
  }

  @PutMapping("/{id}/activate")
  public UserDTOPublic activate(@PathVariable Long id) throws NotFoundException, OperationNotAllowed {
    return userService.updateActive(id, true);
  }

  @PutMapping("/{id}/desactivate")
  public UserDTOPublic desactivate(@PathVariable Long id) throws NotFoundException, OperationNotAllowed {
    return userService.updateActive(id, false);
  }

  //HU26. Borrar usuario

}
