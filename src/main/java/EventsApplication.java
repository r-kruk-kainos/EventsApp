import com.hubspot.dropwizard.guice.GuiceBundle;
import configuration.EventsApplicationConfiguration;
import configuration.EventsApplicationModule;
import domain.User;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import com.github.dirkraft.dropwizard.fileassets.FileAssetsBundle;


public class EventsApplication extends Application<EventsApplicationConfiguration> {

    private GuiceBundle<EventsApplicationConfiguration> guiceBundle;
    private EventsApplicationModule module = new EventsApplicationModule();

    private final HibernateBundle<EventsApplicationConfiguration> hibernateBundle = new HibernateBundle<EventsApplicationConfiguration>(User.class) {
        @Override
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
    }

    public static void main(final String[] args) throws Exception {
        new EventsApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<EventsApplicationConfiguration> bootstrap) {
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
