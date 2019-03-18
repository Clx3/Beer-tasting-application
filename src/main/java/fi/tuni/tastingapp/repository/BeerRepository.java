package fi.tuni.tastingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.Beer;

public interface BeerRepository extends CrudRepository<Beer, Long> {

}
