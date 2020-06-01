package project.mundofii.crawlers.price;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import project.mundofii.crawlers.price.UolEconomiaPriceCrawler;
import project.mundofii.crawlers.price.exception.PriceNotExtractedException;

public class UolEconomiaPriceCrawlerTest {
	
	@Test
	public void testValid() throws IOException, PriceNotExtractedException {	
		
		assertNotNull(new UolEconomiaPriceCrawler("ABCP11").crawl());
	}
	
	@Test(expected=PriceNotExtractedException.class)
	public void testInvalid() throws IOException, PriceNotExtractedException {
		new UolEconomiaPriceCrawler("ABCP12").crawl();
	}

}
