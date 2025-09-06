package com.janitri.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.janitri.qa.base.BaseTest;
import com.janitri.qa.pages.LoginPage;

public class LoginPageTest extends BaseTest {

    @Test(description = "Verify login button's state when fields are empty")
    public void testLoginButtonStateWhenFieldsAreEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled on page load.");
    }
    
    @Test(description = "Validate password masking and unmasking toggle")
    public void testPasswordMasking() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getPasswordInputType(), "password", "Password is not masked by default.");
        loginPage.clickPasswordVisibilityToggle();
        Assert.assertEquals(loginPage.getPasswordInputType(), "text", "Password is not unmasked after clicking the toggle.");
    }

    @Test(description = "Enter random credentials and capture the error message")
    public void testInvalidLoginShowsErrorMsg() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("randomuser@test.com");
        loginPage.enterPassword("somepassword");
        loginPage.clickLoginButton();
        String actualErrorMessage = loginPage.getErrorMessageText();
        System.out.println("Captured error message: " + actualErrorMessage);
        String expectedErrorMessage = "Invalid Credentials";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message is not correct.");
    }
}