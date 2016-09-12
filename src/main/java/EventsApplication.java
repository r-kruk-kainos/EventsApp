import auth.SimpleAuthenticator;
import com.hubspot.dropwizard.guice.GuiceBundle;
import configuration.EventsApplicationConfiguration;
import configuration.EventsApplicationModule;
import dao.AccessTokenDao;
import domain.AccessToken;
import domain.Event;
import domain.Person;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import com.github.dirkraft.dropwizard.fileassets.FileAssetsBundle;
import org.joda.time.DateTimeZone;
import resources.OAuth2Resource;
import resources.PingResource;


public class EventsApplication extends Application<EventsApplicationConfiguration> {

    private GuiceBundle<EventsApplicationConfiguration> guiceBundle;
    private EventsApplicationModule module = new EventsApplicationModule();

    private final HibernateBundle<EventsApplicationConfiguration> hibernateBundle = new HibernateBundle<EventsApplicationConfiguration>(Person.class, Event.class, AccessToken.class) {
        public DataSourceFactory getDataSourceFactory(EventsApplicationConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final MigrationsBundle<EventsApplicationConfiguration> migrationsBundle = new MigrationsBundle<EventsApplicationConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(EventsApplicationConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public void run(EventsApplicationConfiguration configuration, Environment environment) throws Exception {
        module.setSessionFactory(hibernateBundle.getSessionFactory());
        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<Person>()
                        .setAuthenticator(new SimpleAuthenticator(new AccessTokenDao()))
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(guiceBundle.getInjector().getInstance(OAuth2Resource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(PingResource.class));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Person.class));
    }

    public static void main(final String[] args) throws Exception {
        new EventsApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<EventsApplicationConfiguration> bootstrap) {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        bootstrap.addBundle(new FileAssetsBundle("src/main/resources/assets", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(migrationsBundle);

        guiceBundle = GuiceBundle.<EventsApplicationConfiguration>newBuilder()
                .addModule(module)
                .setConfigClass(EventsApplicationConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);
    }
}
