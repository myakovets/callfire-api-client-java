package com.callfire.api.client.api;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.JsonConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Base api test class
 */
public class AbstractApiTest {
    protected static final String BASE_PATH = "/com/callfire/api/client/api";
    protected static final String FIELDS = "id,name,created";
    protected static final String ENCODED_FIELDS = "fields=" + encode(FIELDS);
    protected static final Long TEST_ID = 100500L;
    protected static final String EMPTY_ID_MSG = "id cannot be null";
    protected static final String EMPTY_REQUEST_ID_MSG = "request.id cannot be null";
    protected static final String EMPTY_REQUEST_NUMBER_MSG = "number cannot be null";

    @Rule
    public ExpectedException ex = ExpectedException.none();

    protected CallfireClient client;
    protected JsonConverter jsonConverter;

    @Spy
    protected HttpClient mockHttpClient;
    @Mock
    protected CloseableHttpResponse mockHttpResponse;

    public AbstractApiTest() {
        client = new CallfireClient("login", "password");
        mockHttpClient = client.getRestApiClient().getHttpClient();
        jsonConverter = client.getRestApiClient().getJsonConverter();
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    protected String getJsonPayload(String path) {
        try {
            if (!path.startsWith(BASE_PATH)) {
                path = BASE_PATH + path;
            }
            StringBuilder result = new StringBuilder();
            for (String line : Files.readAllLines(Paths.get(this.getClass().getResource(path).toURI()))) {
                line = StringUtils.trim(line);
                line = line.replaceAll(": ", ":");
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String extractHttpEntity(HttpUriRequest request) throws IOException {
        if (request instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest entityRequest = (HttpEntityEnclosingRequest) request;
            return EntityUtils.toString(entityRequest.getEntity());
        }
        return null;
    }

    protected ArgumentCaptor<HttpUriRequest> mockHttpResponse() throws Exception {
        return mockHttpResponse(null, 200);
    }

    protected ArgumentCaptor<HttpUriRequest> mockHttpResponse(String responseJson) throws Exception {
        return mockHttpResponse(responseJson, 200);
    }

    protected ArgumentCaptor<HttpUriRequest> mockHttpResponse(Integer responseCode) throws Exception {
        return mockHttpResponse(null, responseCode);
    }

    protected ArgumentCaptor<HttpUriRequest> mockHttpResponse(String responseJson, Integer responseCode)
        throws Exception {
        when(mockHttpResponse.getStatusLine()).thenReturn(getStatusForCode(responseCode));
        if (responseJson != null) {
            when(mockHttpResponse.getEntity()).thenReturn(EntityBuilder.create().setText(responseJson).build());
        }

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);
        doReturn(mockHttpResponse).when(mockHttpClient).execute(captor.capture());

        return captor;
    }

    protected static String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void assertUriContainsQueryParams(URI uri, String... params) {
        for (String param : params) {
            assertThat(uri.toString(), containsString(param));
        }
    }

    protected StatusLine getStatusForCode(int code) {
        return new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), code, "OK");
    }
}
