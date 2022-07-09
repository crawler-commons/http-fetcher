# http-fetcher

The Crawler Commons' http-fetcher is a Java library that provides common page fetching functionality needed in web crawlers.
Currently, it uses Apache HttpClient library to implement low-level HTTP communication.

## Requirements
Currently, http-fetcher requires Java 8+.

## API

An example of creating a fetcher with five threads that will only accept content identified by the server as `text/html`:

``` java
// Data passed to UserAgent will be used to automatically create HTTP header 'User-Agent'
UserAgent userAgent = new UserAgent("mycrawler", "crawler@domain.com", "http://domain.com");

// Instantiate the BaseFetcher object used to fetch pages
BaseFetcher fetcher = new SimpleHttpFetcher(1, userAgent);

// Configure the accepted mime-types
Set<String> validMimeTypes = new HashSet<>();
validMimeTypes.add("text/html");
fetcher.setValidMimeTypes(validMimeTypes);

try {
  // Fetch the web page from the Web
  FetchedResult result = fetcher.get("http://localhost:8089/");
  
  // Read downloaded content (additional data is available via remaining methods from FetchedResult object)
  String requestedUrl = result.getBaseUrl(); // the requested URL (same as above)
  String finalUrl = result.getFetchedUrl(); // the final URL after redirects (if any)
  byte[] page = result.getContent(); // the page data returned by server as a byte array
  long fetchTime = result.getFetchTime(); // the time taken to download the page
  String address = result.getHostAddress(); // the host address
} catch (BaseFetchException e) {
  // The download has failed. Check the actual subclass of BaseFetchException to get error details.
}
```
