package com.example.databasesync.rest;

import com.example.databasesync.domain.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CityRestRepository extends PagingAndSortingRepository<City, Long> {
}
