package com.itranswarp.jsonstream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Builder for create JsonReader much easier.
 * 
 * @author Michael Liao
 */
public class JsonBuilder {

    JsonObjectFactory jsonObjectFactory = null;
    JsonArrayFactory jsonArrayFactory = null;
    ObjectHook objectHook = null;

    public JsonBuilder() {
    }

    public JsonBuilder useJsonObjectFactory(JsonObjectFactory jsonObjectFactory) {
        this.jsonObjectFactory = jsonObjectFactory;
        return this;
    }

    public JsonBuilder useJsonArrayFactory(JsonArrayFactory jsonArrayFactory) {
        this.jsonArrayFactory = jsonArrayFactory;
        return this;
    }

    public JsonBuilder useObjectHook(ObjectHook objectHook) {
        this.objectHook = objectHook;
        return this;
    }

    public JsonReader createReader(String str) {
        return createReader(new StringReader(str));
    }

    public JsonReader createReader(Reader reader) {
        return new JsonReader(reader, jsonObjectFactory, jsonArrayFactory, objectHook);
    }

    public JsonReader createReader(InputStream input) {
        try {
            return createReader(new InputStreamReader(input, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}