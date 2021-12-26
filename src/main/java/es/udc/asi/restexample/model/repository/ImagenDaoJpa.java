package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Imagen;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

@Repository
public class ImagenDaoJpa extends GenericDaoJpa implements ImagenDao {
  @Override
  public void create(Imagen imagen) {
    entityManager.persist(imagen);
  }
}