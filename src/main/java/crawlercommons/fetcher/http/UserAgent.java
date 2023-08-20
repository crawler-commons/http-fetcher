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

import crawlercommons.util.HttpFetcherVersion;

/**
 * User Agent enables us to describe characteristics of any http-fetcher
 * agent. There are a number of configurable options to describe the following:
 * <ol>
 * <li><code>agentName</code>: Primary agent name.</li>
 * <li><code>emailAddress</code>: The agent owners email address.</li>
 * <li><code>webAddress</code>: A web site/address representing the agent owner.</li>
 * <li><code>browserVersion</code>: Browser version used for compatibility.</li>
 * <li><code>crawlerVersion</code>: Version of the user agents personal crawler. If
 * this is not set, it defaults to the http-fetcher maven artifact version.</li>
 * <li><code>userAgentString</code>: (Optional) the user-agent string is constructed
 * automatically if not provided, but can be fully customized via this option.</li>
 * </ol>
 * <p>
 * To construct an UserAgent, you can use a <code>UserAgent.Builder</code> object:
 * <pre>
 * {@code
 *     UserAgent ua = new UserAgent.Builder()
 *             .setAgentName("MyCrawler")
 *             .setCrawlerVersion("1.0")
 *             .setWebAddress("www.mycrawler.com/bot.html")
 *             .setUserAgentString("Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P)")
 *             .build();
 * }
 * </pre>
 */
@SuppressWarnings("serial")
public class UserAgent implements Serializable {

    public static final String DEFAULT_BROWSER_VERSION = "Mozilla/5.0";
    public static final String DEFAULT_CRAWLER_VERSION = HttpFetcherVersion.getVersion();

    private final String agentName;
    private final String emailAddress;
    private final String webAddress;
    private final String browserVersion;
    private final String crawlerVersion;
    private final String crawlerConfiguration;
    private final String userAgentString;

    /**
     * Set user agent characteristics
     *
     * @param agentName    an agent name string to associate with the crawler
     * @param emailAddress an agent email address string to associate with the crawler
     * @param webAddress   a Web address string to associate with the crawler
     */
    public UserAgent(String agentName, String emailAddress, String webAddress) {
        this(agentName, emailAddress, webAddress, DEFAULT_BROWSER_VERSION);
    }

    /**
     * Set user agent characteristics
     *
     * @param agentName      an agent name string to associate with the crawler
     * @param emailAddress   an agent email address string to associate with the crawler
     * @param webAddress     a Web address string to associate with the crawler
     * @param browserVersion a browser version to mimic
     */
    public UserAgent(String agentName, String emailAddress, String webAddress, String browserVersion) {
        this(agentName, emailAddress, webAddress, browserVersion, DEFAULT_CRAWLER_VERSION);
    }

    /**
     * Set user agent characteristics
     *
     * @param agentName      an agent name string to associate with the crawler
     * @param emailAddress   an agent email address string to associate with the crawler
     * @param webAddress     a Web address string to associate with the crawler
     * @param browserVersion a browser version to mimic
     * @param crawlerVersion the version of your crawler/crawl agent
     */
    public UserAgent(String agentName, String emailAddress, String webAddress, String browserVersion, String crawlerVersion) {
        this.agentName = agentName;
        this.emailAddress = emailAddress;
        this.webAddress = webAddress;
        this.browserVersion = browserVersion;
        this.crawlerVersion = crawlerVersion;
        this.crawlerConfiguration = crawlerVersion == null ? "" : "/" + crawlerVersion;
        this.userAgentString = createUserAgentString();
    }

    public UserAgent(Builder builder) {
        this.agentName = builder.agentName;
        this.emailAddress = builder.emailAddress;
        this.webAddress = builder.webAddress;
        this.browserVersion = builder.browserVersion;
        this.crawlerVersion = builder.crawlerVersion;
        if (builder.crawlerVersion == null) {
            this.crawlerConfiguration = "";
        } else {
            this.crawlerConfiguration = "/" + builder.crawlerVersion;
        }
        if (builder.userAgentString == null) {
            this.userAgentString = createUserAgentString();
        } else {
            this.userAgentString = builder.userAgentString;
        }
    }

    /**
     * Obtain just the user agent name
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
        return userAgentString;
    }

    /**
     * Creates a string representing the user agent characteristics. The format is as follows:
     * "Mozilla/5.0 (compatible; mycrawler/1.0; +http://www.mydomain.com; mycrawler@mydomain.com)"
     *
     * @return the User-Agent string
     */
    private String createUserAgentString() {
        StringBuilder sb = new StringBuilder();
        sb.append(browserVersion);
        sb.append(" (compatible; ");
        sb.append(agentName);
        sb.append(crawlerConfiguration);
        final boolean webAddressIsSet = webAddress != null && !webAddress.isEmpty();
        final boolean emailAddressIsSet = emailAddress != null && !emailAddress.isEmpty();
        if (webAddressIsSet || emailAddressIsSet) {
            sb.append("; ");
        }
        if (webAddressIsSet) {
            sb.append("+");
            sb.append(webAddress);
        }
        if (emailAddressIsSet) {
            if (webAddressIsSet) {
                sb.append("; ");
            }
            sb.append(emailAddress);
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Builds a user agent with custom characteristics
     */
    public static class Builder {

        private String agentName;
        private String emailAddress;
        private String webAddress;
        private String browserVersion = DEFAULT_BROWSER_VERSION;
        private String crawlerVersion = DEFAULT_CRAWLER_VERSION;
        private String userAgentString;

        public Builder() {
        }

        public Builder setAgentName(String agentName) {
            this.agentName = agentName;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setWebAddress(String webAddress) {
            this.webAddress = webAddress;
            return this;
        }

        public Builder setBrowserVersion(String browserVersion) {
            this.browserVersion = browserVersion;
            return this;
        }

        public Builder setCrawlerVersion(String crawlerVersion) {
            this.crawlerVersion = crawlerVersion;
            return this;
        }

        public Builder setUserAgentString(String userAgentString) {
            this.userAgentString = userAgentString;
            return this;
        }

        public UserAgent build() {
            return new UserAgent(this);
        }
    }
}
