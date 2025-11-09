package com.example.umc9th.global.validator;

import com.example.umc9th.domain.category.repository.CategoryRepository;
import com.example.umc9th.global.annotation.ExistCategories;
import com.example.umc9th.global.apiPayload.code.status.CategoryErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final CategoryRepository categoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream().allMatch(categoryRepository::existsById);

        if(!isValid){
            context.disableDefaultConstraintViolation(); // 디폴트 메세지 초기화
            context.buildConstraintViolationWithTemplate(CategoryErrorCode.CATEGORY_NOT_EXISTED.getMessage())
                    .addConstraintViolation(); // 새로운 메세지로 덮어쓰기
        }
        return isValid;
    }
}
