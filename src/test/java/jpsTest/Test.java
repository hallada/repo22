package jpsTest;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Test {
	WebDriver driver;
	DesiredCapabilities dc;
	//RemoteWebDriver driver;
	@Before
	public void setUpt() {
		
	}
	
	@org.junit.Test
	public void test() throws InterruptedException, MalformedURLException {
		
		String navo = System.getProperty("browser");
		if(navo==null) {
			dc = DesiredCapabilities.chrome();
			//driver = new ChromeDriver();
			System.out.println("Chrome");
		}
		else if (navo.equals("firefox")) {
			//System.setProperty("webdriver.gecko.driver","C:\\Users\\Formation\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			//driver = new FirefoxDriver();
			dc = DesiredCapabilities.firefox();
			System.out.println("Firefox");
		} else {
			//driver = new ChromeDriver();
			dc = DesiredCapabilities.chrome();
			System.out.println("Default Chrome");
		}
		 
		driver = new RemoteWebDriver(new URL("http://localhost:444/wd/hub"), dc);
		driver.get("http://localhost:8282/jpetstore/");

		// Premiere page
		WebElement linkEnter = driver.findElement(By.xpath("//*[@id=\"Content\"]/p[1]/a"));
		linkEnter.click();
		Thread.sleep(1000);
		// Second Page
		WebElement linkFish = driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]"));
		linkFish.click();
		// Vérification du tableau
		assertEquals("Angelfish", driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[2]")).getText());
		// Fermeture Fenetre + session
		driver.quit();
	}
}
