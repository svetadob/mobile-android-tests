package com.example;

import com.example.config.AppConfig;
import com.example.config.Environment;
import com.example.execution.AndroidAppInstance;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

@ContextConfiguration(classes = {AppConfig.class})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    protected static final String DEFAULT_PROVIDER = "default-provider";

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private Environment environment;

    @DataProvider(name = DEFAULT_PROVIDER)
    protected Object[][] defaultDataProvider() {
        return new Object[][] {
                {new AndroidAppInstance(environment)}
        };
    }
}
