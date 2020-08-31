package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class SignInPage extends BasePage {
    @AndroidFindBy(id = "emailView")
    private MobileElement emailField;

    @AndroidFindBy(id = "passwordView")
    private MobileElement passwordField;

    @AndroidFindBy(id = "signInView")
    private MobileElement signInButton;

    public SignInPage(AppiumDriver<MobileElement> aDriver) {
        super(aDriver);
    }

    @Step("Sign in with existing user with login {0} and password {1}")
    public HomePage signIn(String login, String password) {
        emailField.sendKeys(login);
        passwordField.sendKeys(password);
        signInButton.click();

        return new HomePage(getDriver());
    }
}
