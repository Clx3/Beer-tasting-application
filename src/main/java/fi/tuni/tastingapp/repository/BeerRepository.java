package fi.tuni.tastingapp.repository;

import java.util.List;
import java.util.Optional;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.Beer;

public interface BeerRepository extends CrudRepository<Beer, Long> {
	
	public List<Beer> findAll();
	
	public List<Beer> findByIdIn(List<Long> id);

	public Optional<Beer> findById(long beerId);

}
