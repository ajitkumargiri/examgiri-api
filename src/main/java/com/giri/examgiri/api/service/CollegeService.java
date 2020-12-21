package com.giri.examgiri.api.service;


import com.giri.examgiri.api.domain.College;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link College}.
 */
public interface CollegeService {

    /**
     * Save a college.
     *
     * @param college the entity to save.
     * @return the persisted entity.
     */
    College save(College college);

    /**
     * Get all the colleges.
     *
     * @return the list of entities.
     */
    List<College> findAll();


    /**
     * Get the "id" college.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<College> findOne(Long id);

    /**
     * Delete the "id" college.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
