package com.giri.examgiri.api.service;


import com.giri.examgiri.api.domain.College;
import com.giri.examgiri.api.repository.CollegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link College}.
 */
@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {

    private final Logger log = LoggerFactory.getLogger(CollegeServiceImpl.class);

    private final CollegeRepository collegeRepository;

    public CollegeServiceImpl(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @Override
    public College save(College college) {
        log.debug("Request to save College : {}", college);
        return collegeRepository.save(college);
    }

    @Override
    @Transactional(readOnly = true)
    public List<College> findAll() {
        log.debug("Request to get all Colleges");
        return collegeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<College> findOne(Long id) {
        log.debug("Request to get College : {}", id);
        return collegeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete College : {}", id);
        collegeRepository.deleteById(id);
    }
}
