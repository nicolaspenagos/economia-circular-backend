package com.icesi.economiacircularicesi.validation;

import lombok.SneakyThrows;
import org.passay.*;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PasswordValidator implements ConstraintValidator<CustomAnnotation.PasswordValidation, String> {


    @Override
    public void initialize(CustomAnnotation.PasswordValidation passwordValidation) {
        // Do nothing because of is autowired
    }

    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("passay.properties");
        props.load(inputStream);
        MessageResolver resolver = new PropertiesMessageResolver(props);

        org.passay.PasswordValidator validator = new org.passay.PasswordValidator(resolver, Arrays.asList(

                // at least one digit letter
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),
                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1)

        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}