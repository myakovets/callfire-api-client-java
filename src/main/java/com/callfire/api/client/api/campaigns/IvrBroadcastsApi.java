package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.IvrBroadcast;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindIvrBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.api.callstexts.CallsApi.LIST_OF_CALLS_TYPE;
import static com.callfire.api.client.api.callstexts.CallsApi.PAGE_OF_CALLS_TYPE;
import static com.callfire.api.client.api.campaigns.BatchesApi.PAGE_OF_BATCH_TYPE;

/**
 * Represents rest endpoint /campaigns/ivrs
 *
 * @since 1.0
 */
public class IvrBroadcastsApi {
    private static final String IVR_PATH = "/campaigns/ivrs";
    private static final String IVR_ITEM_PATH = "/campaigns/ivrs/{}";
    private static final String IVR_ITEM_BATCHES_PATH = "/campaigns/ivrs/{}/batches";
    private static final String IVR_ITEM_CALLS_PATH = "/campaigns/ivrs/{}/calls";
    private static final String IVR_ITEM_START_PATH = "/campaigns/ivrs/{}/start";
    private static final String IVR_ITEM_STOP_PATH = "/campaigns/ivrs/{}/stop";
    private static final String IVR_ITEM_ARCHIVE_PATH = "/campaigns/ivrs/{}/archive";
    private static final String IVR_ITEM_RECIPIENTS_PATH = "/campaigns/ivrs/{}/recipients";
    private static final TypeReference<IvrBroadcast> IVR_TYPE = new TypeReference<IvrBroadcast>() {
    };
    private static final TypeReference<Page<IvrBroadcast>> PAGE_OF_IVRS_TYPE = new TypeReference<Page<IvrBroadcast>>() {
    };

    private RestApiClient client;

    public IvrBroadcastsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find ivr broadcasts by name, label, etc...
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link IvrBroadcast} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<IvrBroadcast> find(FindIvrBroadcastsRequest request) {
        return client.get(IVR_PATH, PAGE_OF_IVRS_TYPE, request);
    }

    /**
     * Create ivr broadcast
     *
     * @param broadcast ivr broadcast to create
     * @return {@link ResourceId} object with id of created broadcast
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(IvrBroadcast broadcast) {
        return create(broadcast, null);
    }

    /**
     * Create ivr broadcast
     *
     * @param broadcast ivr broadcast to create
     * @param start     if set to true broadcast will starts immediately
     * @return {@link ResourceId} object with id of created broadcast
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(IvrBroadcast broadcast, Boolean start) {
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("start", start, queryParams);
        return client.post(IVR_PATH, RESOURCE_ID_TYPE, broadcast, queryParams);
    }

    /**
     * Get ivr broadcast by id
     *
     * @param id id of broadcast
     * @return {@link IvrBroadcast} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public IvrBroadcast get(Long id) {
        return get(id, null);
    }

    /**
     * Get ivr broadcast by id
     *
     * @param id     id of broadcast
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link IvrBroadcast} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public IvrBroadcast get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(IVR_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), IVR_TYPE, queryParams);
    }

    /**
     * Update ivr broadcast
     *
     * @param broadcast broadcast to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(IvrBroadcast broadcast) {
        Validate.notNull(broadcast.getId(), "broadcast.id cannot be null");
        client.put(IVR_ITEM_PATH.replaceFirst(PLACEHOLDER, broadcast.getId().toString()), VOID_TYPE, broadcast);
    }

    /**
     * Get ivr broadcast batches. Retrieve batches associated with ivr campaign
     *
     * @param request get request
     * @return {@link Page} with {@link Batch} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Batch> getBatches(GetByIdRequest request) {
        String path = IVR_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_BATCH_TYPE, request);
    }

    /**
     * Add batch to ivr broadcast
     *
     * @param request request with contacts
     * @return {@link ResourceId} with id of created {@link Batch}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId addBatch(AddBatchRequest request) {
        String path = IVR_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, RESOURCE_ID_TYPE, request);
    }

    /**
     * Get ivr broadcast calls.
     * Get calls associated with ivr broadcast ordered by date
     *
     * @param request request with properties to filter
     * @return {@link Page} with {@link Call} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Call> getCalls(GetByIdRequest request) {
        String path = IVR_ITEM_CALLS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_CALLS_TYPE, request);
    }

    /**
     * Starts IVR campaign
     *
     * @param id id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void start(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(IVR_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Stops IVR campaign
     *
     * @param id id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void stop(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(IVR_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Archives IVR campaign
     *
     * @param id id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void archive(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(IVR_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Add recipients to ivr broadcast
     *
     * @param id         id of ivr broadcast
     * @param recipients recipients to add
     * @return list of {@link Call} to recipients
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Call> addRecipients(Long id, List<Recipient> recipients) {
        return addRecipients(id, recipients, null);
    }

    /**
     * Add recipients to ivr broadcast
     *
     * @param id         id of ivr broadcast
     * @param recipients recipients to add
     * @param fields     limit fields returned. Example fields=id,message
     * @return list of {@link Call} to recipients
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Call> addRecipients(Long id, List<Recipient> recipients, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = IVR_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, LIST_OF_CALLS_TYPE, recipients, queryParams).getItems();
    }
}