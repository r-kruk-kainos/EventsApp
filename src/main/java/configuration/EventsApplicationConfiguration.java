package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.dropwizard.db.DataSourceFactory;


public class EventsApplicationConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @JsonProperty
    private ImmutableList<String> allowedGrantTypes;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }


}
