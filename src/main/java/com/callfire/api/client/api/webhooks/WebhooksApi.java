package com.callfire.api.client.api.webhooks;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.webhooks.model.Webhook;
import com.callfire.api.client.api.webhooks.model.request.FindWebhooksRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /webhooks
 *
 * @since 1.0
 */
public class WebhooksApi {
    private static final String WEBHOOKS_PATH = "/webhooks";
    private static final String WEBHOOKS_ITEM_PATH = "/webhooks/{}";
    private static final TypeReference<Webhook> WEBHOOK_TYPE = new TypeReference<Webhook>() {
    };
    private static final TypeReference<Page<Webhook>> PAGE_OF_WEBHOOKS_TYPE = new TypeReference<Page<Webhook>>() {
    };

    private RestApiClient client;

    public WebhooksApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find webhooks by name, event, etc.
     *
     * @param request request object with different fields to filter
     * @return {@link Page} with {@link Webhook} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Webhook> find(FindWebhooksRequest request) {
        return client.get(WEBHOOKS_PATH, PAGE_OF_WEBHOOKS_TYPE, request);
    }

    /**
     * Get webhook by id.
     *
     * @param id id of webhook
     * @return {@link Webhook} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Webhook get(Long id) {
        return get(id, null);
    }

    /**
     * Get webhook by id.
     *
     * @param id     id of webhook
     * @param fields limit fields returned. Example fields=id,name
     * @return {@link Webhook} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Webhook get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = WEBHOOKS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, WEBHOOK_TYPE, queryParams);
    }

    /**
     * Create webhook
     *
     * @param webhook webhook to create
     * @return {@link ResourceId} object with id of created {@link Webhook}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(Webhook webhook) {
        return client.post(WEBHOOKS_PATH, RESOURCE_ID_TYPE, webhook);
    }

    /**
     * Update webhook
     *
     * @param webhook webhook to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(Webhook webhook) {
        Validate.notNull(webhook.getId(), "webhook.id cannot be null");
        client.put(WEBHOOKS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(webhook.getId())),
            VOID_TYPE, webhook);
    }

    /**
     * Delete webhook by id
     *
     * @param id id of webhook
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(WEBHOOKS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(id)));
    }
}