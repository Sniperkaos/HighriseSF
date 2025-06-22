package me.cworldstar.highriseSF.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;

import me.cworldstar.highriseSF.HighriseSF;

public class AutoUpdater {
	public AutoUpdater() {
        URI AutoUpdateLink = URI.create("https://github.com/Sniperkaos/HighriseSF/releases/latest");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest updateCrawler = HttpRequest.newBuilder(AutoUpdateLink).header("Accept", "application/json").GET().build();
   
        try {
			HttpResponse<String> crawlerResponse = client.send(updateCrawler, HttpResponse.BodyHandlers.ofString());
			//JsonElement element = JsonParser.parseString(crawlerResponse.body());
			HighriseSF.get().getLogger().log(Level.INFO, crawlerResponse.toString());
        } catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}        
	}
}
