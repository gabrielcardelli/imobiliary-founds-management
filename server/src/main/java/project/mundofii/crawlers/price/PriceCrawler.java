package project.mundofii.crawlers.price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.mundofii.crawlers.price.exception.PriceNotExtractedException;

public abstract class PriceCrawler implements Crawler<BigDecimal> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String bovCode;
	
	public PriceCrawler(String bovCode) {
		this.bovCode = bovCode;
	}

	public BigDecimal crawl() throws IOException, PriceNotExtractedException{
		URLConnection connection = new URL(crawlUrl().replace("{BOVCODE}", bovCode)).openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		connection.connect();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String linha = null;
		
		String cotacao = null;
		
		while((linha = reader.readLine()) != null) {
				
			cotacao = extract(linha);
			
			if(cotacao != null) {
				break;
			}
			
		}
		
		if(cotacao == null) {
			throw new PriceNotExtractedException();
		}
		
		log.debug("Extracting price of " + bovCode + " on " + this.getClass().getSimpleName() + " - Price Extracted: " + cotacao);
		
		return new BigDecimal(cotacao.replace(".", "").replace(",", "."));
			
	}

	public abstract String extract(String linha);
	
	public abstract String crawlUrl();
	
}
