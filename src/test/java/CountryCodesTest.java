import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;


public class CountryCodesTest {

    private int successResponseCode, notFoundResponseCode;
    private String url;

    @BeforeClass
    @Parameters({"url", "successResponseCode", "notFoundResponseCode"})
    void initParameters(String url, int successResponseCode, int notFoundResponseCode) {
        this.successResponseCode = successResponseCode;
        this.notFoundResponseCode = notFoundResponseCode;
        this.url = url;
    }

    @Test
    @Parameters({"countryNameJson", "countryCode", "countryName"})
    public void getCountryNameByCode(String countryNameJson, String countryCode, String countryCapital) {
        get(url + countryNameJson)
                .then().statusCode(successResponseCode)
                .assertThat().body(countryCode, Matchers.equalTo(countryCapital));
    }

    @Test
    @Parameters({"countryCapitalJson", "countryCode", "countryCapital"})
    public void getCountryCapital(String countryCapitalJson, String countryCode, String countryCapital) {
        get(url + countryCapitalJson)
                .then().statusCode(successResponseCode)
                .assertThat().body(countryCode, Matchers.equalTo(countryCapital));
    }

    @Test
    @Parameters({"incorrectCountryName"})
    public void getInexistentCountry(String incorrectCountryName) {
        get(url + incorrectCountryName)
                .then().statusCode(notFoundResponseCode);
    }


}