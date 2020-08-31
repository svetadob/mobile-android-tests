package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class BasePage {
    @Getter(AccessLevel.PROTECTED)
    private AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> aDriver) {
        driver = aDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }
}
