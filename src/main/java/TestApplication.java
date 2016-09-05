import configuration.TestApplicationConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.setup.Bootstrap;
import com.github.dirkraft.dropwizard.fileassets.FileAssetsBundle;


public class TestApplication extends Application<TestApplicationConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(TestApplication.class);

    public void run(TestApplicationConfiguration configuration, Environment environment) throws Exception {

    }

    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TestApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new FileAssetsBundle("src/main/resources/assets", "/", "index.html", "TEST"));
    }
}
