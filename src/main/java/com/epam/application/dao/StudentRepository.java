package com.epam.application.dao;

import com.epam.application.model.Intern;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Intern,Long> {
}
