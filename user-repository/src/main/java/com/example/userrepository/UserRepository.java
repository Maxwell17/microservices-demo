package com.example.userrepository;

import com.example.userrepository.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepository extends CrudRepository<User, Long> {
}
