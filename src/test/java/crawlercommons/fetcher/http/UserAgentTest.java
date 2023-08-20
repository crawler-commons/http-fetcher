package crawlercommons.fetcher.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Locale;

public class UserAgentTest {
    String agentName = "mycrawler";
    String mail = "mycrawler@mydomain.com";
    String webAddress = "http://www.mydomain.com";
    String browserVersion = "Mozilla/5.0";
    String crawlerVersion = "0.1";

    @Test
    public void testUserAgentFullConstructor() {
        UserAgent ua = new UserAgent(agentName, mail, webAddress, browserVersion, crawlerVersion);
        assertEquals(
                "Mozilla/5.0 (compatible; mycrawler/0.1; +http://www.mydomain.com; mycrawler@mydomain.com)",
                ua.getUserAgentString());
        assertEquals(agentName, ua.getAgentName());
    }

    @Test
    public void testUserAgentDefaultConstructor() {
        UserAgent ua = new UserAgent(agentName, mail, webAddress);
        assertEquals(
                String.format(Locale.getDefault(),
                        "%s (compatible; mycrawler/%s; +http://www.mydomain.com; mycrawler@mydomain.com)",
                        UserAgent.DEFAULT_BROWSER_VERSION, UserAgent.DEFAULT_CRAWLER_VERSION),
                ua.getUserAgentString());
        assertEquals(agentName, ua.getAgentName());
    }

    @Test
    public void buildUserAgentString1() {
        UserAgent ua = new UserAgent.Builder()
            .setAgentName("MyCrawler")
            .setCrawlerVersion("1.0")
            .build();
        assertEquals("Mozilla/5.0 (compatible; MyCrawler/1.0)", ua.getUserAgentString());
        assertEquals("MyCrawler", ua.getAgentName());
    }

    @Test
    public void buildUserAgentString2() {
        UserAgent ua = new UserAgent.Builder()
            .setAgentName("MyCrawler")
            .setCrawlerVersion("1.0")
            .setWebAddress("www.mycrawler.com/bot.html")
            .build();
        assertEquals("Mozilla/5.0 (compatible; MyCrawler/1.0; +www.mycrawler.com/bot.html)", ua.getUserAgentString());
        assertEquals("MyCrawler", ua.getAgentName());
    }

    @Test
    public void buildUserAgentString3() {
        UserAgent ua = new UserAgent.Builder()
            .setAgentName("MyCrawler")
            .setCrawlerVersion("1.0")
            .setEmailAddress("bot@mycrawler.com")
            .build();
        assertEquals("Mozilla/5.0 (compatible; MyCrawler/1.0; bot@mycrawler.com)", ua.getUserAgentString());
        assertEquals("MyCrawler", ua.getAgentName());
    }

    @Test
    public void buildUserAgentString4() {
        UserAgent ua = new UserAgent.Builder()
            .setAgentName("MyCrawler")
            .setCrawlerVersion("1.0")
            .setWebAddress("www.mycrawler.com/bot.html")
            .setEmailAddress("bot@mycrawler.com")
            .build();
        assertEquals("Mozilla/5.0 (compatible; MyCrawler/1.0; +www.mycrawler.com/bot.html; bot@mycrawler.com)", ua.getUserAgentString());
        assertEquals("MyCrawler", ua.getAgentName());
    }

    @Test
    public void buildUserAgentString5() {
        UserAgent ua = new UserAgent.Builder()
            .setAgentName("MyCrawler")
            .setCrawlerVersion("1.0")
            .setWebAddress("www.mycrawler.com/bot.html")
            .setUserAgentString("Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P)")
            .build();
        assertEquals("Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P)", ua.getUserAgentString());
        assertEquals("MyCrawler", ua.getAgentName());
    }

    @Test
    public void buildUserAgentString6() {
        UserAgent ua = new UserAgent.Builder()
                .setAgentName("MyCrawler")
                .setCrawlerVersion("1.0")
                .setBrowserVersion("Mozilla/6.0")
                .setWebAddress("www.mycrawler.com/bot.html")
                .setEmailAddress("bot@mycrawler.com")
                .build();
        assertEquals("Mozilla/6.0 (compatible; MyCrawler/1.0; +www.mycrawler.com/bot.html; bot@mycrawler.com)", ua.getUserAgentString());
        assertEquals("MyCrawler", ua.getAgentName());
    }
}
