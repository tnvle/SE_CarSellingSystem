package edu.mum.cs.cs425.finalproject.carmanagement.model.validators;

import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueDealerNumberValidator implements ConstraintValidator<UniqueDealerNumber, String> {

    private IDealerService dealerService;

    public UniqueDealerNumberValidator() {
    }

    @Autowired
    public UniqueDealerNumberValidator(IDealerService dealerService) {
        this.dealerService = dealerService;
    }

    public void initialize(UniqueDealerNumber constraintAnnotation) {
    }

    public boolean isValid(String dealerNumber, ConstraintValidatorContext context) {
        boolean valid;
        if (dealerService != null) {
            valid = (dealerNumber != null && !dealerService.findByDealerNumber(dealerNumber).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
