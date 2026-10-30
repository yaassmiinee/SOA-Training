import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Index extends ResourceConfig {
    public Index() {
        packages("webservices", "filtres");
    }
}