# http-fetcher
Wrapper code for Apache HttpClient that provides common page fetching functionality

TODO - add more context here.

An example of creating a fetcher with five threads that will only accept content identified by the server as text/html:

``` java
BaseFetcher fetcher = new SimpleHttpFetcher(1, new UserAgent("mycrawler", "crawler@domain.com", "http://domain.com"));
Set<String> validMimeTypes = new HashSet<String>();
validMimeTypes.add("text/html");
fetcher.setValidMimeTypes(validMimeTypes);
FetchedResult result = fetcher.get("http://localhost:8089/");
```
