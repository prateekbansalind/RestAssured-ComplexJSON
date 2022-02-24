import files.ReUsableMethods;
import files.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses(){
        var responseBody = ReUsableMethods.stringToJson(ResponseBody.complexJsonResponse());
        int expectedPriceAmount = responseBody.getInt("dashboard.purchaseAmount");
        int actualPriceAmount = 0;
        for (int i = 0; i < responseBody.getInt("courses.size()"); i++){
            int priceAmount = responseBody.getInt("courses["+i+"].copies") * responseBody.getInt("courses["+i+"].price");
            actualPriceAmount += priceAmount;
        }
        System.out.println("Expected Price Amount : " + expectedPriceAmount);
        System.out.println("Actual Price Amount : " + actualPriceAmount);
        Assert.assertEquals(actualPriceAmount, expectedPriceAmount);
    }

}
