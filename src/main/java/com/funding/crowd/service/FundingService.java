package com.funding.crowd.service;

import com.funding.crowd.domain.Funding;
import com.funding.crowd.repository.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FundingService {

    @Autowired
    FundingRepository fundingRepository;

    public List<Funding> findAll() {
        return fundingRepository.findAll();
    }

    public Funding findById(Long id) {
        return fundingRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Funding save(Funding funding) {
        return fundingRepository.save(funding);
    }

    public void delete(Funding funding) {
        fundingRepository.delete(funding);
    }
}
