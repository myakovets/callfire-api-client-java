package com.callfire.api.client.model.request;

import com.callfire.api.client.ConvertToString;
import com.callfire.api.client.model.Text.State;
import com.callfire.api.client.model.TextRecord.TextResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request object for GET /texts which incapsulates
 * different query pairs
 */
public class FindTextsRequest extends FindCallsTextsRequest {
    @ConvertToString
    private List<State> states = new ArrayList<>();
    @ConvertToString
    private List<TextResult> results = new ArrayList<>();

    private FindTextsRequest() {
    }

    /**
     * Get text statuses
     *
     * @return text statuses
     */
    public List<State> getStates() {
        return states;
    }

    /**
     * Get text results
     *
     * @return list of text results
     */
    public List<TextResult> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("states", states)
            .append("results", results)
            .toString();
    }

    /**
     * Builder class
     */
    public static class FindTextsRequestBuilder extends FindCallsTextsRequestBuilder<FindTextsRequestBuilder> {
        private FindTextsRequest request;

        private FindTextsRequestBuilder() {
            request = new FindTextsRequest();
        }

        public static FindTextsRequestBuilder create() {
            return new FindTextsRequestBuilder();
        }

        /**
         * Set text statuses to filter
         *
         * @param states list of states to filter
         */
        public FindTextsRequestBuilder setStates(List<State> states) {
            request.states = states;
            return this;
        }

        /**
         * Set text results
         *
         * @param results text results to set
         */
        public FindTextsRequestBuilder setResults(List<TextResult> results) {
            request.results = results;
            return this;
        }

        @Override
        public FindTextsRequest build() {
            return request;
        }

        @Override
        protected FindTextsRequestBuilder getChild() {
            return this;
        }

        @Override
        protected FindTextsRequest getRequest() {
            return request;
        }
    }
}
