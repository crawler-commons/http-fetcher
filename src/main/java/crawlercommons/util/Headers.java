package crawlercommons.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A container for HTTP headers.
 */
@SuppressWarnings("serial")
public class Headers implements Serializable {
    
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LOCATION = "Content-Location";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String LOCATION = "Location";

    private final List<String> EMPTY_VALUES = Collections.unmodifiableList(Collections.emptyList());

    /**
     * A map of all headers.
     */
    private final Map<String, List<String>> headers;
    
    public Headers() {
        this.headers = new HashMap<>();
    }
    
    public Headers(Map<String, List<String>> headers) {
        this.headers = new HashMap<>();
    }

    /**
     * Returns true if named value is multivalued.
     * 
     * @param name name of header
     * @return true if named value is multivalued, false if single value or null
     */
    public boolean isMultiValued(final String name) {
        List<String> values = headers.get(name);
        return values != null && values.size() > 1;
    }

    /**
     * Returns an array of header names.
     * 
     * @return header names
     */
    public List<String> names() {
        return new ArrayList<String>(headers.keySet());
    }

    /**
     * Get the value associated to a header name. If many values are associated
     * to the specified name, then the first one is returned.
     * 
     * @param name
     *          of the header.
     * @return the value associated to the specified header name.
     */
    public String get(final String name) {
        List<String> values = headers.get(name);
        return values == null ? null : values.get(0);
    }

    /**
     * Get the values associated to a header name.
     * 
     * @param name
     *          of the header.
     * @return the values associated to a header name.
     */
    public List<String> getValues(final String name) {
        return _getValues(name);
    }

    private List<String> _getValues(final String name) {
        List<String> values = headers.get(name);
        return values == null ? EMPTY_VALUES : values;
    }
    
    /**
     * Add a header name/value mapping. Add the specified value to the list of
     * values associated to the specified header name.
     * 
     * @param name
     *          the header name.
     * @param value
     *          the header value.
     */
    public void add(final String name, final String value) {
        List<String> values = headers.get(name);
        if (values == null) {
            set(name, value);
        } else {
            values.add(value);
        }
    }
    
    /**
     * Set header name/value. Associate the specified value to the specified
     * header name. If some previous values were associated to this name,
     * they are removed. If the given value is <code>null</code>, then the
     * header entry is removed.
     *
     * @param name the header name.
     * @param value  the header value, or <code>null</code>
     */
    public void set(String name, String value) {
        if (value != null) {
            List<String> values = new ArrayList<>(1);
            values.add(value);
            headers.put(name, values);
        } else {
            headers.remove(name);
        }
    }

    /**
     * Returns the number of header names in this Header.
     * 
     * @return number of header names
     */
    public int size() {
        return headers.size();
    }

    /**
     * Returns the HTTP headers as a plain Java map object. The map keys are
     * the header names and map values are the header content.
     * 
     * @return headers data as plain Java map.
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        List<String> names = names();
        for (int i = 0; i < names.size(); i++) {
            List<String> values = _getValues(names.get(i));
            for (int j = 0; j < values.size(); j++) {
                buf.append(names.get(i))
                   .append("=")
                   .append(values.get(j))
                   .append(" ");
            }
        }
        return buf.toString();
    }

}