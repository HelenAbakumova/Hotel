package controller;

import entity.LoginUser;
import entity.RegistrationUser;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

        public static final String REGPASSWORD = "^[A-Za-z0-9!@#$%^&*()_]{6,20}$";
        private static final String WRONG_CREDENTIALS_ERROR = " Wrong credentials";
        public static final String PLEASE_TRY_AGAIN = "your creds are incorrect, please try again.";
        public static final String PRINTABLE_CONSTANT_NAME_ERR = "PrintableConstant.NAME_ERR";
        public static final String PRINTABLE_CONSTANT_WRONG_NAME_MSG = "PrintableConstant.WRONG_NAME_MSG";
        public static final String PRINTABLE_CONSTANT_LAST_NAME_ERR = "PrintableConstant.LAST_NAME_ERR";
        public static final String PRINTABLE_CONSTANT_WRONG_LAST_NAME_MSG = "PrintableConstant.WRONG_LAST_NAME_MSG";
        public static final String PRINTABLE_CONSTANT_EMAIL_ERR = "PrintableConstant.EMAIL_ERR";
        public static final String PRINTABLE_CONSTANT_WRONG_EMAIL_MSG = "PrintableConstant.WRONG_EMAIL_MSG";
        public static final String PRINTABLE_CONSTANT_PASSWORD_ERR = "PrintableConstant.PASSWORD_ERR";
        public static final String PRINTABLE_CONSTANT_WRONG_PASSWORD_MSG = "PrintableConstant.WRONG_PASSWORD_MSG";
        public static final String PRINTABLE_CONSTANT_PASSWORDS_NOT_EQUAL_MSG = "PrintableConstant.PASSWORDS_NOT_EQUAL_MSG";
        public static final String REGUSERNAME = "^[A-Za-z]{3,20}$";
        public static final String REGEMAIL = "^[a" +"-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";


        public Map<String, String> validateRegForm(RegistrationUser registrationUser) {
            Map<String, String> errors = new HashMap<>();
            if (!validateName(registrationUser.getName())) {
                errors.put(PRINTABLE_CONSTANT_NAME_ERR, PRINTABLE_CONSTANT_WRONG_NAME_MSG);
            }

            if (!validateEmail(registrationUser.getEmail())) {
                errors.put(PRINTABLE_CONSTANT_EMAIL_ERR, PRINTABLE_CONSTANT_WRONG_EMAIL_MSG);
            }
            if (!validatePassword(registrationUser.getPassword())) {
                errors.put(PRINTABLE_CONSTANT_PASSWORD_ERR, PRINTABLE_CONSTANT_WRONG_PASSWORD_MSG);
            }
            if (!validateRePassword(registrationUser.getPassword(), registrationUser.getRePassword())) {
                errors.put(PRINTABLE_CONSTANT_PASSWORD_ERR, PRINTABLE_CONSTANT_PASSWORDS_NOT_EQUAL_MSG);
            }
            return errors;
        }

        public Map<String, String> validateLoginForm(LoginUser loginUser) {
            Map<String, String> errors = new HashMap<>();
            if (!validateEmail(loginUser.getEmail())) {
                errors.put(WRONG_CREDENTIALS_ERROR, PLEASE_TRY_AGAIN);
            }
            if (!validatePassword(loginUser.getPassword())) {
                errors.put(WRONG_CREDENTIALS_ERROR, PLEASE_TRY_AGAIN);
                return errors;
            }
            return errors;
        }

        private boolean validateName(String name) {
            String regUsername = REGUSERNAME;
            return name.matches(regUsername);
        }

        private boolean validateEmail(String email) {
            String regEmail = REGEMAIL;
            return email.matches(regEmail);
        }

        private boolean validatePassword(String password) {
            String regPassword = REGPASSWORD;
            return password.matches(regPassword);
        }

        private boolean validateRePassword(String password, String rePassword) {
            return password.equals(rePassword);
        }





}