## Mobile Android testing framework

The current framework is built to test the user interface of a Android application

### Technologies
<ul>
<li><a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">Java 8</a></li>
<li><a href="https://testng.org/doc/">TestNG</a></li>
<li><a href="https://github.com/allure-framework/allure-maven">Allure</a></li>
<li><a href="http://appium.io/">Appium</a></li>
<li><a href="https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html">Spring-test</a></li>
<li><a href="https://projectlombok.org/">Lombok</a></li>
</ul>

### Reporting
You can generate Allure report using one of the following command:
<ul>
<li>Report will be generated into temp folder. Web server with results will start.</li>

```shell script
mvn allure:serve
```

<li>Report will be generated tо directory: target/site/allure-maven/index.html</li>

```shell script
mvn allure:report
```
</ul>

### Test scenarios
Common assumptions:
- Toggles state is saved right after tap on it so switching to other page will not affect it
- Test user used in each of the test should be newly created one with default toggles state


Scenarios:
<ul>
<li>Verify default toggles state and initiate Equity Alert turning off but cancel it in alert so state should remain enabled</li>
<li>Verify that both toggles could be turned off and it's state will remain after Settings reopening</li>
<li>Verify that each toggle could be change separately and it will not affect another one</li>
</ul>

Given scenarios are a smoke suite for toggles switching verification.
It covers possible switch states.