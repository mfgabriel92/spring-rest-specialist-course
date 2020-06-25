package br.gabriel.springrestspecialist.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private DataSize value;
    
    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.value = DataSize.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file == null || file.getSize() <= this.value.toBytes();
    }
}