package com.bbt.model;

import android.os.StrictMode;

import org.restlet.engine.Engine;
import org.restlet.engine.connector.HttpClientHelper;
import org.restlet.ext.jackson.JacksonConverter;

public class EngineConfiguration {

    private static EngineConfiguration  ourInstance = new EngineConfiguration();
    public final static String  gae_path = "http://bbt-backend.appspot.com";

    public static EngineConfiguration getInstance() {
        return ourInstance;
    }

    /**
     * Restlet Engine Basic Configuration
     */
    public EngineConfiguration() {
        /* Very importance Policy, HTTP cannot work without theses two lines*/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /* Jackson Lib & Client Helper Registration */
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());
        Engine.getInstance().getRegisteredClients().add(new HttpClientHelper(null));
    }

    public static EngineConfiguration getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(EngineConfiguration ourInstance) {
        EngineConfiguration.ourInstance = ourInstance;
    }
}