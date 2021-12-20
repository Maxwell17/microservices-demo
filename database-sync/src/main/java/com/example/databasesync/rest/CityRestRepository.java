package com.example.databasesync.rest;

import com.example.databasesync.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cities", collectionResourceRel = "cities")
public interface CityRestRepository extends CrudRepository<City, Long> {
}
