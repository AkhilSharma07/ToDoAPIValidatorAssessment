package com.assignment.todoAPIFanCodeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fancode.common.TestBase;
import com.fancode.config.Config;
import com.fancode.model.Todo;
import com.fancode.model.User;
import com.fancode.utils.CityHelper;
import org.testng.Assert;
import org.testng.ITestResult;
import io.restassured.response.Response;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoAPIFanCodeTest extends TestBase {
	
	 private ExtentReports extent;
	 private ExtentSparkReporter spark;
	 private ExtentTest logger;

	 @BeforeTest
	 public void initializeReport() {
		    // Getting the current timestamp for dynamic report naming
		    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		    // Setting up the report directory and file path
		    String reportDirectory = "Reports";
		    String reportFileName = "FanCodeTestResults_" + timestamp + ".html";
		    String reportFilePath = reportDirectory + File.separator + reportFileName;

		    // Creating the report directory if it doesn't exist
		    File reportDir = new File(reportDirectory);
		    if (!reportDir.exists()) {
		        reportDir.mkdirs();
		    }

		    // Initializing ExtentReports and ExtentSparkReporter
		    extent = new ExtentReports();
		    spark = new ExtentSparkReporter(reportFilePath);
		    extent.attachReporter(spark);
	 		}
	 
    @Test
    public void validateUsersFromFanCodeCity() {
    	
    	logger = extent.createTest("Validating users who have completed more than 50% of their tasks");
    	
        Response userResponse = given().baseUri(Config.getBaseUri())
                .when().get("/users");
        Assert.assertEquals(userResponse.getStatusCode(), 200, "Failed to fetch users");
        logger.pass("Successfully fetched users from the endpoint");

        // Converting response to User array
        User[] users = userResponse.as(User[].class);

        //Iterate through the list of users
        for (User user : users) {
            double lat = Double.parseDouble(user.getAddress().getGeo().getLat());
            double lng = Double.parseDouble(user.getAddress().getGeo().getLng());

            // Checking if the user belongs to FanCode city
            if (CityHelper.isInFanCodeCity(lat, lng)) {
                // Fetching todos for the user
                Response todoResponse = given().baseUri(Config.getBaseUri())
                        .queryParam("userId", user.getId())
                        .when().get("/todos");
                Assert.assertEquals(todoResponse.getStatusCode(), 200, "Failed to fetch todos for user: " + user.getId());

                // Converting response to Todo array
                Todo[] todos = todoResponse.as(Todo[].class);

                // Calculating task completion percentage
                double completionPercentage = CityHelper.calculateCompletionPercentage(todos);
                logger.info("User " + user.getName() + " has completed " + completionPercentage + "% of tasks.");

                //Validating if the completion percentage is more than 50%
                Assert.assertTrue(completionPercentage > 50, "User " + user.getName() + " has less than 50% tasks completed.");
            }
        }
    }
    
    @AfterMethod
    public void logTestResult(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Failed", ExtentColor.RED));
                logger.fail(result.getThrowable());
                break;
            case ITestResult.SKIP:
                logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Skipped", ExtentColor.ORANGE));
                break;
            case ITestResult.SUCCESS:
                logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Passed", ExtentColor.GREEN));
                break;
            default:
                logger.log(Status.INFO, MarkupHelper.createLabel(result.getName() + " - Test Status Unknown", ExtentColor.BLUE));
        }
    }

    @AfterTest
    public void tearDownReporting() {
        extent.flush();
    }
}