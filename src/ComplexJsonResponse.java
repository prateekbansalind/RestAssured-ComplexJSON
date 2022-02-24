import files.ReUsableMethods;
import files.ResponseBody;
import org.testng.Assert;

public class ComplexJsonResponse {

	public static void main(String[] args) {

		// responseBody contains a response in JSON format received from the server.
		var responseBody = ReUsableMethods.stringToJson(ResponseBody.complexJsonResponse());

		// 1. Print No of courses returned by API
		int noOfCourses = responseBody.getInt("courses.size()");
		System.out.println("No of Courses: " + noOfCourses);

		// 2.Print Purchase Amount
		int totalPurchaseAmount = responseBody.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase Amount: " + totalPurchaseAmount);

		// 3. Print Title of the first course
		String titleOfFirstCourse = responseBody.getString("courses[0].title");
		System.out.println("Title of first course in the JSON response : " + titleOfFirstCourse);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < noOfCourses; i++)
			System.out.println(responseBody.getString("courses["+i+"].title") + " : " + responseBody.getInt("courses["+i+"].price"));
			// here one thing to remember, in case we are not assigning an integer result into any dedicated integer variable and directly
		    // calling in the system.out.println then always end the argument with the toString() method.
			// System.out.println(responseBody.get(("course["+i+"],price").toString());

		// 5. Print no of copies sold by RPA Course
		for (int i = 0; i < noOfCourses; i++){
			if (responseBody.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
			{
				System.out.println(responseBody.getString("courses["+i+"].title") + " : " + responseBody.getInt("courses["+i+"].price"));
			    break;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		int actualPriceAmount = 0;
		for (int i = 0; i < noOfCourses; i++){
			int totalPrice = responseBody.getInt("courses["+i+"].price") * responseBody.getInt("courses["+i+"].copies");
			actualPriceAmount += totalPrice;
		}
		System.out.println("Actual total price of all the courses sold : " + actualPriceAmount);
		Assert.assertEquals(actualPriceAmount, totalPurchaseAmount);

	}

}
