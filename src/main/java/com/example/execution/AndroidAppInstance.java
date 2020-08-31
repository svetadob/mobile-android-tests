package com.example.execution;

import com.example.config.Environment;
import com.example.pages.StartPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

@RequiredArgsConstructor
public class AndroidAppInstance {
    private ThreadLocal<AppiumDriver<MobileElement>> appiumDriver = new ThreadLocal<>();
    private final Environment environment;

    public AppiumDriver<MobileElement> getDriver() {
        return isDriverExist() ? appiumDriver.get() : createDriver();
    }

    @Step("Start application")
    public StartPage start() {
        return new StartPage(getDriver());
    }

    public void cleanDriver() {
        if (isDriverExist()) {
            appiumDriver.get().quit();
            appiumDriver.remove();
        }
    }

    public boolean isDriverExist() {
        return appiumDriver.get() != null;
    }

    private AppiumDriver<MobileElement> createDriver() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, environment.getApp());
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, environment.isFullResetApp());
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, environment.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, environment.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, environment.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        appiumDriver.set(new AppiumDriver<>(appiumServiceBuilder, capabilities));

        return appiumDriver.get();
    }
}
