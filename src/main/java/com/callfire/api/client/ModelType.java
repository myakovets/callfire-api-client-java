package com.callfire.api.client;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.ApiCredentials;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import com.callfire.api.client.api.account.model.CallerId;
import com.callfire.api.client.api.account.model.CreditsUsage;
import com.callfire.api.client.api.account.model.NumberOrder;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.Media;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.campaigns.model.AgentSession;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.CallBroadcast;
import com.callfire.api.client.api.campaigns.model.CallBroadcastStats;
import com.callfire.api.client.api.campaigns.model.CallRecording;
import com.callfire.api.client.api.campaigns.model.CampaignSound;
import com.callfire.api.client.api.campaigns.model.CccCampaign;
import com.callfire.api.client.api.campaigns.model.TextAutoReply;
import com.callfire.api.client.api.campaigns.model.TextBroadcast;
import com.callfire.api.client.api.campaigns.model.TextBroadcastStats;
import com.callfire.api.client.api.common.model.ErrorMessage;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.ContactList;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.UniversalDnc;
import com.callfire.api.client.api.keywords.model.Keyword;
import com.callfire.api.client.api.keywords.model.KeywordLease;
import com.callfire.api.client.api.numbers.model.Number;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.numbers.model.Region;
import com.callfire.api.client.api.webhooks.model.Webhook;
import com.callfire.api.client.api.webhooks.model.WebhookResource;

/**
 * Class contains TypeReferences for all model objects
 */
public final class ModelType {
    // @formatter:off
    public static final TypeReference<Map<String, String>> MAP_OF_STRINGS_TYPE = new TypeReference<Map<String, String>>() {};
    // @formatter:on
    private static final Map<Class, TypeReference> SIMPLE_TYPES = new HashMap<>();
    private static final Map<Class, TypeReference> LIST_TYPES = new HashMap<>();
    private static final Map<Class, TypeReference> PAGEABLE_TYPES = new HashMap<>();
    private static final Map<Class, TypeReference> LISTHOLDER_TYPES = new HashMap<>();

    static {
        initSimpleTypes();
        initListTypes();
        initPageTypes();
        initListHolderTypes();
    }

    private static void initSimpleTypes() {
        // @formatter:off
        SIMPLE_TYPES.put(Void.class, new TypeReference<Void>() {});
        SIMPLE_TYPES.put(String.class, new TypeReference<String>() {});
        SIMPLE_TYPES.put(Boolean.class, new TypeReference<Boolean>() {});
        SIMPLE_TYPES.put(InputStream.class, new TypeReference<InputStream>() {});

        SIMPLE_TYPES.put(ResourceId.class, new TypeReference<ResourceId>() {});
        SIMPLE_TYPES.put(Account.class, new TypeReference<Account>() {});
        SIMPLE_TYPES.put(ApiCredentials.class, new TypeReference<ApiCredentials>() {});
        SIMPLE_TYPES.put(BillingPlanUsage.class, new TypeReference<BillingPlanUsage>() {});
        SIMPLE_TYPES.put(CreditsUsage.class, new TypeReference<CreditsUsage>() {});
        SIMPLE_TYPES.put(NumberOrder.class, new TypeReference<NumberOrder>() {});
        SIMPLE_TYPES.put(AgentGroup.class, new TypeReference<AgentGroup>() {});
        SIMPLE_TYPES.put(Agent.class, new TypeReference<Agent>() {});
        SIMPLE_TYPES.put(AgentSession.class, new TypeReference<AgentSession>() {});
        SIMPLE_TYPES.put(Batch.class, new TypeReference<Batch>() {});
        SIMPLE_TYPES.put(Media.class, new TypeReference<Media>() {});
        SIMPLE_TYPES.put(CallBroadcast.class, new TypeReference<CallBroadcast>() {});
        SIMPLE_TYPES.put(CallBroadcastStats.class, new TypeReference<CallBroadcastStats>() {});
        SIMPLE_TYPES.put(TextBroadcastStats.class, new TypeReference<TextBroadcastStats>() {});
        SIMPLE_TYPES.put(CampaignSound.class, new TypeReference<CampaignSound>() {});
        SIMPLE_TYPES.put(TextAutoReply.class, new TypeReference<TextAutoReply>() {});
        SIMPLE_TYPES.put(CccCampaign.class, new TypeReference<CccCampaign>() {});
        SIMPLE_TYPES.put(TextBroadcast.class, new TypeReference<TextBroadcast>() {});
        SIMPLE_TYPES.put(Call.class, new TypeReference<Call>() {});
        SIMPLE_TYPES.put(Text.class, new TypeReference<Text>() {});
        SIMPLE_TYPES.put(ContactList.class, new TypeReference<ContactList>() {});
        SIMPLE_TYPES.put(Contact.class, new TypeReference<Contact>() {});
        SIMPLE_TYPES.put(ContactHistory.class, new TypeReference<ContactHistory>() {});
        SIMPLE_TYPES.put(ErrorMessage.class, new TypeReference<ErrorMessage>() {});
        SIMPLE_TYPES.put(NumberLease.class, new TypeReference<NumberLease>() {});
        SIMPLE_TYPES.put(NumberConfig.class, new TypeReference<NumberConfig>() {});
        SIMPLE_TYPES.put(KeywordLease.class, new TypeReference<KeywordLease>() {});
        SIMPLE_TYPES.put(Webhook.class, new TypeReference<Webhook>() {});
        SIMPLE_TYPES.put(WebhookResource.class, new TypeReference<WebhookResource>() {});
        SIMPLE_TYPES.put(CallRecording.class, new TypeReference<CallRecording>() {});
        SIMPLE_TYPES.put(UniversalDnc.class, new TypeReference<UniversalDnc>() {});
        SIMPLE_TYPES.put(DoNotContact.class, new TypeReference<DoNotContact>() {});
        // @formatter:on
    }

    private static void initListTypes() {
        // @formatter:off
        LIST_TYPES.put(AgentGroup.class, new TypeReference<List<AgentGroup>>() {});
        LIST_TYPES.put(Agent.class, new TypeReference<List<Agent>>() {});
        // @formatter:on
    }

    private static void initPageTypes() {
        // @formatter:off
        PAGEABLE_TYPES.put(ApiCredentials.class, new TypeReference<Page<ApiCredentials>>() {});
        PAGEABLE_TYPES.put(AgentGroup.class, new TypeReference<Page<AgentGroup>>() {});
        PAGEABLE_TYPES.put(Agent.class, new TypeReference<Page<Agent>>() {});
        PAGEABLE_TYPES.put(AgentSession.class, new TypeReference<Page<AgentSession>>() {});
        PAGEABLE_TYPES.put(CampaignSound.class, new TypeReference<Page<CampaignSound>>() {});
        PAGEABLE_TYPES.put(TextAutoReply.class, new TypeReference<Page<TextAutoReply>>() {});
        PAGEABLE_TYPES.put(CccCampaign.class, new TypeReference<Page<CccCampaign>>() {});
        PAGEABLE_TYPES.put(CallBroadcast.class, new TypeReference<Page<CallBroadcast>>() {});
        PAGEABLE_TYPES.put(TextBroadcast.class, new TypeReference<Page<TextBroadcast>>() {});
        PAGEABLE_TYPES.put(Batch.class, new TypeReference<Page<Batch>>() {});
        PAGEABLE_TYPES.put(Call.class, new TypeReference<Page<Call>>() {});
        PAGEABLE_TYPES.put(Text.class, new TypeReference<Page<Text>>() {});
        PAGEABLE_TYPES.put(ContactList.class, new TypeReference<Page<ContactList>>() {});
        PAGEABLE_TYPES.put(Contact.class, new TypeReference<Page<Contact>>() {});
        PAGEABLE_TYPES.put(DoNotContact.class, new TypeReference<Page<DoNotContact>>() {});
        PAGEABLE_TYPES.put(Region.class, new TypeReference<Page<Region>>() {});
        PAGEABLE_TYPES.put(KeywordLease.class, new TypeReference<Page<KeywordLease>>() {});
        PAGEABLE_TYPES.put(NumberLease.class, new TypeReference<Page<NumberLease>>() {});
        PAGEABLE_TYPES.put(NumberConfig.class, new TypeReference<Page<NumberConfig>>() {});
        PAGEABLE_TYPES.put(Webhook.class, new TypeReference<Page<Webhook>>() {});
        PAGEABLE_TYPES.put(Media.class, new TypeReference<Page<Media>>() {});

        // @formatter:on
    }

    private static void initListHolderTypes() {
        // @formatter:off
        LISTHOLDER_TYPES.put(ResourceId.class, new TypeReference<ListHolder<ResourceId>>() {});
        LISTHOLDER_TYPES.put(CallerId.class, new TypeReference<ListHolder<CallerId>>() {});
        LISTHOLDER_TYPES.put(Call.class, new TypeReference<ListHolder<Call>>() {});
        LISTHOLDER_TYPES.put(Text.class, new TypeReference<ListHolder<Text>>() {});
        LISTHOLDER_TYPES.put(UniversalDnc.class, new TypeReference<ListHolder<UniversalDnc>>() {});
        LISTHOLDER_TYPES.put(Keyword.class, new TypeReference<ListHolder<Keyword>>() {});
        LISTHOLDER_TYPES.put(Number.class, new TypeReference<ListHolder<Number>>() {});
        LISTHOLDER_TYPES.put(WebhookResource.class, new TypeReference<ListHolder<WebhookResource>>() {});
        LISTHOLDER_TYPES.put(CallRecording.class, new TypeReference<ListHolder<CallRecording>>() {});
        // @formatter:on
    }

    private ModelType() {
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeReference<T> of(Class<T> type) {
        return safeGet(SIMPLE_TYPES, type);
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeReference<List<T>> listOf(Class<T> type) {
        return safeGet(LIST_TYPES, type);
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeReference<ListHolder<T>> listHolderOf(Class<T> type) {
        return safeGet(LISTHOLDER_TYPES, type);
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeReference<Page<T>> pageOf(Class<T> type) {
        return safeGet(PAGEABLE_TYPES, type);
    }

    private static TypeReference safeGet(Map<Class, TypeReference> map, Class type) {
        if (!map.containsKey(type)) {
            throw new IllegalStateException(
                "Map with TypeReferences doesn't contain following type: " + type.getName());
        }
        return map.get(type);
    }
}
