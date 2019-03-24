package test;

import base.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;
import util.ImageResponse;
import util.LinkResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HomePageTest extends BaseTest {
    public HomePageTest() {

    }

    @Test
    public void exportBoutiqueLinkResponses() {
        HomePage homePage = new HomePage(driver);

        homePage.getPage(strUrlWeb);
        homePage.isHomePage();
        homePage.closeIfHomepagePopup();
        List<LinkResponse> linkResponses = homePage.getBoutiqueLinksAndResponseCodes();

        try {
            String separator = ",";
            FileWriter writer = new FileWriter("csv\\link-responses.csv");
            writer.write("Link,Status Code\n");
            for (LinkResponse linkResponse : linkResponses) {
                StringBuilder csvBuilder = new StringBuilder();
                csvBuilder.append(linkResponse.link);
                csvBuilder.append(separator);
                csvBuilder.append(linkResponse.responseCode);
                csvBuilder.append("\n");
                String csv = csvBuilder.toString();
                writer.write(csv);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void exportBoutiqueImageLinkResponseTimes() {
        HomePage homePage = new HomePage(driver);

        homePage.getPage(strUrlWeb);
        homePage.isHomePage();
        homePage.closeIfHomepagePopup();
        List<ImageResponse> imageResponses = homePage.getBoutiqueImageLinksAndResponseTimes();

        try {
            String separator = ",";
            FileWriter writer = new FileWriter("csv\\image-responses.csv");
            writer.write("Image Link,Status Code,Response Time\n");
            for (ImageResponse imageResponse : imageResponses) {
                StringBuilder csvBuilder = new StringBuilder();
                csvBuilder.append(imageResponse.imageLink);
                csvBuilder.append(separator);
                csvBuilder.append(imageResponse.responseCode);
                csvBuilder.append(separator);
                csvBuilder.append(imageResponse.responseTime);
                csvBuilder.append("\n");
                String csv = csvBuilder.toString();
                writer.write(csv);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}