package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IDealerRepository extends JpaRepository<Dealer, Long> {
    Optional<Dealer> findDealerByDealerNumber(String dealerNumber);
    Optional<Dealer> findDealerByEmail(String email);

    Page<Dealer> findAllByDealerNumberContainingOrNameContainingOrAddressContainingOrWebsiteContainingOrPhoneNumberContainingOrEmailContaining
            (String dealerNumber, String name, String address, String website, String phoneNumber, String email, Pageable pageable);

}
