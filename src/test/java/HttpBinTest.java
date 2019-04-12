import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;

public class HttpBinTest {

    private int successResponseCode;
    private String url;

    @BeforeClass
    @Parameters({"successResponseCode", "url"})
    void initParameters(int successResponseCode, String url) {
        this.successResponseCode = successResponseCode;
        this.url = url;
    }

    @Test
    @Parameters({"acceptedEncoding"})
    public void getRequest(String acceptedEncoding) {
        get(url + "get")
                .then().statusCode(successResponseCode)
                .assertThat().body("headers.Accept-Encoding", Matchers.equalTo(acceptedEncoding));
    }

    @Test
    public void postRequest() {
        post(url + "post")
                .then().statusCode(successResponseCode);
    }

}
