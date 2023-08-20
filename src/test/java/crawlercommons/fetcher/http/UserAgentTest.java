package crawlercommons.fetcher.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


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
                String.format("%s (compatible; mycrawler/%s; +http://www.mydomain.com; mycrawler@mydomain.com)",
                        UserAgent.DEFAULT_BROWSER_VERSION, UserAgent.DEFAULT_CRAWLER_VERSION),
                ua.getUserAgentString());
        assertEquals(agentName, ua.getAgentName());
    }
}
