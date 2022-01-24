package at.stderr;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/version")
public class versionResource {

    @GET
    public String getVersion() {
        var version = System.getenv("QUARKUS_EXAMPLE_VERSION");

        if (version == null || version.equals("")) {
            version = "unknown";
        }
        return version;
    }
}
