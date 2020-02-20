package edu.mum.cs.cs425.finalproject.carmanagement.service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IDealerService {
    public abstract List<Dealer> getAllDealers();

    public abstract Page<Dealer> getAllDealers(int pageNo);

    public abstract Dealer registerNewDealer(Dealer dealer);

    public abstract Dealer getDealerById(Long dealerId);
    public abstract Dealer getDealerByEmail(String email);
    public abstract Dealer saveDealer(Dealer dealer);

    public abstract void deleteDealerById(Long dealerId);

    public abstract Optional<Dealer> findByDealerNumber(String dealerNumber);

    public abstract Page<Dealer> searchDealers(String searchString, int pageNo);
}   
