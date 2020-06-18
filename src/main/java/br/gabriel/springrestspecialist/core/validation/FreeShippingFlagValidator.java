package br.gabriel.springrestspecialist.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class FreeShippingFlagValidator implements ConstraintValidator<FreeShippingFlag, Object> {
    private String fieldValue;

    private String fieldDescription;

    private String mandatoryFlag;

    @Override
    public void initialize(FreeShippingFlag constraintAnnotation) {
        this.fieldValue = constraintAnnotation.fieldValue();
        this.fieldDescription = constraintAnnotation.fieldDescription();
        this.mandatoryFlag = constraintAnnotation.mandatoryFlag();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;

        try {
            BigDecimal val = (BigDecimal) BeanUtils.getPropertyDescriptor(value.getClass(), fieldValue).getReadMethod().invoke(value);
            String desc = (String) BeanUtils.getPropertyDescriptor(value.getClass(), fieldDescription).getReadMethod().invoke(value);
            
            if (val != null && BigDecimal.ZERO.compareTo(val) == 0 && desc != null) {
                isValid = desc.toLowerCase().contains(mandatoryFlag.toLowerCase());
            }
        
            return isValid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
