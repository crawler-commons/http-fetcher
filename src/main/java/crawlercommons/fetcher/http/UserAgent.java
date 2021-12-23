/**
 * Copyright 2016 Crawler-Commons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package crawlercommons.fetcher.http;

import java.io.Serializable;
import java.util.Locale;

import crawlercommons.util.HttpFetcherVersion;

/**
 * User Agent enables us to describe characteristics of any http-fetcher
 * agent. There are a number of constructor options to describe the following:
 * <ol>
 * <li><code>_agentName</code>: Primary agent name.</li>
 * <li><code>_emailAddress</code>: The agent owners email address.</li>
 * <li><code>_webAddress</code>: A web site/address representing the agent owner.</li>
 * <li><code>_browserVersion</code>: Broswer version used for compatibility.</li>
 * <li><code>_crawlerVersion</code>: Version of the user agents personal crawler. If
 * this is not set, it defaults to the http-fetcher maven artifact version.</li>
 * </ol>
 * 
 */
@SuppressWarnings("serial")
public class UserAgent implements Serializable {

    public static final String DEFAULT_BROWSER_VERSION = "Mozilla/5.0";
    public static final String DEFAULT_CRAWLER_VERSION = HttpFetcherVersion.getVersion();

    private final String agentName;
    private final String emailAddress;
    private final String webAddress;
    private final String browserVersion;
    private final String crawlerConfiguration;

    /**
     * Set user agent characteristics
     * 
     * @param agentName
     *            an agent name string to associate with the crawler
     * @param emailAddress
     *            an agent email address string to associate with the crawler
     * @param webAddress
     *            a Web address string to associate with the crawler
     */
    public UserAgent(String agentName, String emailAddress, String webAddress) {
        this(agentName, emailAddress, webAddress, DEFAULT_BROWSER_VERSION);
    }

    /**
     * Set user agent characteristics
     * 
     * @param agentName
     *            an agent name string to associate with the crawler
     * @param emailAddress
     *            an agent email address string to associate with the crawler
     * @param webAddress
     *            a Web address string to associate with the crawler
     * @param browserVersion
     *            a browser version to mimic
     */
    public UserAgent(String agentName, String emailAddress, String webAddress, String browserVersion) {
        this(agentName, emailAddress, webAddress, browserVersion, DEFAULT_CRAWLER_VERSION);
    }

    /**
     * Set user agent characteristics
     * 
     * @param agentName
     *            an agent name string to associate with the crawler
     * @param emailAddress
     *            an agent email address string to associate with the crawler
     * @param webAddress
     *            a Web address string to associate with the crawler
     * @param browserVersion
     *            a browser version to mimic
     * @param crawlerVersion
     *            the version of your crawler/crawl agent
     */
    public UserAgent(String agentName, String emailAddress, String webAddress, String browserVersion, String crawlerVersion) {
        this.agentName = agentName;
        this.emailAddress = emailAddress;
        this.webAddress = webAddress;
        this.browserVersion = browserVersion;
        this.crawlerConfiguration = crawlerVersion == null ? "" : "/" + crawlerVersion;
    }

    /**
     * Obtain the just the user agent name
     * 
     * @return User Agent name (String)
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * Obtain a String representing the user agent characteristics.
     * 
     * @return User Agent String
     */
    public String getUserAgentString() {
        // Mozilla/5.0 (compatible; mycrawler/1.0; +http://www.mydomain.com;
        // mycrawler@mydomain.com)
        return String.format(Locale.getDefault(), "%s (compatible; %s%s; +%s; %s)", browserVersion, getAgentName(), crawlerConfiguration, webAddress, emailAddress);
    }
}
