package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract request object for controlling calls/texts flags availability
 */
public abstract class CallsTextsRequest extends CallfireModel {
    protected Boolean call;
    protected Boolean text;

    /**
     * Get call flag
     *
     * @return Dnc call flag
     */
    public Boolean getCall() {
        return call;
    }

    /**
     * Get text flag
     *
     * @return Dnc text flag
     */
    public Boolean getText() {
        return text;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("call", call)
            .append("text", text)
            .toString();
    }

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class CallsTextsBuilder<B extends CallsTextsBuilder, R extends CallsTextsRequest>
        extends AbstractBuilder<R> {

        public CallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Set Dnc call flag
         *
         * @param call Dnc call flag
         * @return builder self-reference
         */
        public B call(Boolean call) {
            request.call = call;
            return (B) this;
        }

        /**
         * Set Dnc text flag
         *
         * @param text Dnc text flag
         * @return builder self-reference
         */
        public B text(Boolean text) {
            request.text = text;
            return (B) this;
        }
    }
}
