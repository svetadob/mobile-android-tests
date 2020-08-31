package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class StartPage extends BasePage {
    @AndroidFindBy(id = "btnSkip")
    private MobileElement skipButton;

    @AndroidFindBy(id = "secondary_button")
    private MobileElement laterButton;

    @AndroidFindBy(id = "btnSignIn")
    private MobileElement signInButton;

    public StartPage(AppiumDriver<MobileElement> aDriver) {
        super(aDriver);
    }

    @Step("Skip popups")
    public StartPage skipPopups() {
        skipButton.click();
        laterButton.click();
        return this;
    }

    @Step("Go to sign in with existing account page")
    public SignInPage goToSignIn() {
        signInButton.click();

        return new SignInPage(getDriver());
    }
}
