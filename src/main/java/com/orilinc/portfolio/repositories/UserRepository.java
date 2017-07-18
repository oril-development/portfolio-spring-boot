package com.orilinc.portfolio.repositories;

import com.orilinc.portfolio.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
