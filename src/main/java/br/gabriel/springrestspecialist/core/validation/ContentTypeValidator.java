package br.gabriel.springrestspecialist.core.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ContentTypeValidator implements ConstraintValidator<ContentType, MultipartFile> {
    private List<String> value;
    
    @Override
    public void initialize(ContentType constraintAnnotation) {
        this.value = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return this.value == null || this.value.contains(file.getContentType());
    }
}