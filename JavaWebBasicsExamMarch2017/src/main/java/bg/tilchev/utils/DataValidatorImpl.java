package bg.tilchev.utils;


import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.LinkedHashSet;
import java.util.Set;

@Stateless
public class DataValidatorImpl implements DataValidator {

    private Validator validator;

    public DataValidatorImpl() {
        super();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> Set<String> validateData(T bindingModel) {
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(bindingModel);
        Set<String> errors = new LinkedHashSet<>();
        for (ConstraintViolation<T> constraint : constraintViolations) {
            errors.add(constraint.getMessage());
        }
        return errors;
    }
}
