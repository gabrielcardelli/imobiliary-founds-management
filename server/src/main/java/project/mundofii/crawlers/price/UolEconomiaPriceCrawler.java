package project.mundofii.crawlers.price;

public class UolEconomiaPriceCrawler extends PriceCrawler{

	public UolEconomiaPriceCrawler(String bovCode) {
		super(bovCode);
	}

	public String extract(String linha) {
		
		String extractedInfo = null;
		
		if (linha.contains("class=\"ultima\"")) {
			extractedInfo = linha.trim().replace("<td class=\"ultima\">", "").replace("</td>", "");
		}
		
		return extractedInfo;
		
	}

	@Override
	public String crawlUrl() {
		return "http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo={BOVCODE}"
				+ ".SA&beginDay=1&beginMonth=1&beginYear=2018&endDay=1&endMonth=1&endYear=2018";
	}

}
