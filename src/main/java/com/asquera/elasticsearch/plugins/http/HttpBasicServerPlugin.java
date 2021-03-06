package com.asquera.elasticsearch.plugins.http;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.plugins.Plugin;
//import org.elasticsearch.common.settings.ImmutableSettings;

import java.util.ArrayList;
import java.util.Collection;

//import static org.elasticsearch.common.collect.Lists.*;

/**
 * @author Florian Gilcher (florian.gilcher@asquera.de)
 */
public class HttpBasicServerPlugin extends Plugin {

    private boolean enabledByDefault = true;
    private final Settings settings;

    @Inject public HttpBasicServerPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override public String name() {
        return "http-basic-server-plugin";
    }

    @Override public String description() {
        return "HTTP Basic Server Plugin";
    }

    /*@Override public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = new ArrayList();
        if (settings.getAsBoolean("http.basic.enabled", enabledByDefault)) {
            modules.add(HttpBasicServerModule.class);
        }
        return modules;
    }

    @Override public Collection<Class<? extends LifecycleComponent>> services() {
        Collection<Class<? extends LifecycleComponent>> services = new ArrayList();
        if (settings.getAsBoolean("http.basic.enabled", enabledByDefault)) {
            services.add(HttpBasicServer.class);
        }
        return services;
    }

    @Override public Settings additionalSettings() {
        if (settings.getAsBoolean("http.basic.enabled", enabledByDefault)) {
            return ImmutableSettings.settingsBuilder().
                    put("http.enabled", false).                    
                    build();
        } else {
            return ImmutableSettings.Builder.EMPTY_SETTINGS;
        }
    }*/

    public Collection<Module> nodeModules() {
        Collection modules = new ArrayList();
        if (this.settings.getAsBoolean("http.basic.enabled", Boolean.valueOf(this.enabledByDefault)).booleanValue()) {
            modules.add(new HttpBasicServerModule(this.settings));
        }
        return modules;
    }

    public Collection<Class<? extends LifecycleComponent>> nodeServices() {
        Collection services = new ArrayList();
        if (this.settings.getAsBoolean("http.basic.enabled", Boolean.valueOf(this.enabledByDefault)).booleanValue()) {
            services.add(HttpBasicServer.class);
        }
        return services;
    }

    public Settings additionalSettings() {
        if (this.settings.getAsBoolean("http.basic.enabled", Boolean.valueOf(this.enabledByDefault)).booleanValue()) {
            return Settings.settingsBuilder().put("http.enabled", false).build();
        }

        return Settings.Builder.EMPTY_SETTINGS;
    }
}
