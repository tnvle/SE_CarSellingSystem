package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;


import edu.mum.cs.cs425.finalproject.carmanagement.AbstractECarManagementComponentTest;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Role;
import edu.mum.cs.cs425.finalproject.carmanagement.model.User;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IUserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional // Enables any database operations to rollback after each testing
public class DealerServiceImplTest extends AbstractECarManagementComponentTest {

    @Autowired
    private IDealerService dealerService;

    @Autowired
    private IUserService userService;
    
    @Before
    public void setUp() {
        logger.info("DealerServiceImplTest started");
    }

    @After
    public void tearDown() {
        logger.info("DealerServiceImplTest completed");
    }

    @Test
    public void testGetAllDealers() {
        List<Dealer> dealers = (List<Dealer>)dealerService.getAllDealers();
        Assert.assertNotNull("Failure: expected dealers to be not null", dealers);
        Assert.assertEquals("Failure: expected size", 6, dealers.size());
        logger.info("Dealers list data: " + Arrays.toString(dealers.toArray()));
    }

    @Test
    public void testGetDealerById() {
        Long dealerId = new Long(1);
        Dealer dealer = dealerService.getDealerById(dealerId);
        Assert.assertNotNull("Failure: expected dealer to be not null", dealer);
        Assert.assertEquals("Failure: expected dealerId to match", dealerId, dealer.getDealerId());
        logger.info("Dealer data: " + dealer);
    }

    @Test
    public void testGetDealerByIdForInvalidId() {
        Long dealerId = Long.MAX_VALUE;
        Dealer dealer = dealerService.getDealerById(dealerId);
        Assert.assertNull("Failure: expected null", dealer);
        logger.info("Dealer data: " + dealer);
    }

    @Test
    public void testSaveDealer() {
        User user = userService.getUserById(2);
        Dealer newDealer = new Dealer("DL007", "123 New Jersey", "lavender@mum.edu", "Lavender Store", "test1234", "(641) 5555 1234", "www.lavender.com");
        newDealer.setUser(user);
        Dealer savedDealer = dealerService.saveDealer(newDealer);
        Assert.assertNotNull("Failure: expected not null", savedDealer);
        Assert.assertNotNull("Failure: expected dealerId to be not null", savedDealer.getDealerId());
        Assert.assertEquals("Failure: expected Dealer Name match", "123 New Jersey", savedDealer.getName());
        List<Dealer> dealers = (List<Dealer>)dealerService.getAllDealers();
        Assert.assertEquals("Failure: expected size", 7, dealers.size());
        logger.info("Dealers list data: " + Arrays.toString(dealers.toArray()));
    }

    @Test
    public void testDeleteDealerById() {
        User user = userService.getUserById(2);
        Dealer newDealer = new Dealer("DL007", "123 New Jersey", "lavender@mum.edu", "Harminder Store", "test1234", "(641) 5555 1234", "www.lavender.com");
        newDealer.setUser(user);
        Dealer savedDealer = dealerService.saveDealer(newDealer);
        Assert.assertNotNull("Failure: expected not null", savedDealer);
        List<Dealer> dealersBefore = (List<Dealer>)dealerService.getAllDealers();
        Assert.assertEquals("Failure: expected size before deleting", 7, dealersBefore.size());
        dealerService.deleteDealerById(savedDealer.getDealerId());
        List<Dealer> dealers = (List<Dealer>)dealerService.getAllDealers();
        Assert.assertEquals("Failure: expected size after deleting", 6, dealers.size());
        logger.info("Dealers list data: " + Arrays.toString(dealers.toArray()));
    }

    /* Execute unit-tests via maven on cmdline: mvn clean package */
    /* Execute package only without unit-tests via maven on cmdline:
    /* mvn clean package -DskipTests */
}
