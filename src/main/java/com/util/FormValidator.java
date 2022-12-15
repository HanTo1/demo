package com.util;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class FormValidator extends AbstractValidator {

	@Override
	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
		Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
		
		validateEmail(ctx, (String)beanProps.get("email").getValue());
	}

	private void validateEmail(ValidationContext ctx, String email) {
		if (email == null || !email.matches(".+@.+\\.[a-z]+")) {
			this.addInvalidMessage(ctx, "email", "Please enter a valid email!");
		}
	}

}
