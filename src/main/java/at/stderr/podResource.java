package at.stderr;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;

@Path("/pods")
public class podResource {

    private final KubernetesClient k8sClient;

    public podResource(KubernetesClient k8sClient) {
	this.k8sClient = k8sClient;
    }

    @GET
    @Path("{namespace}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pods(@PathParam("namespace") String namespace) {
	List<Pod> pods = Collections.<Pod>emptyList();

	try {
	    pods = k8sClient.pods().inNamespace(namespace).list().getItems();
	}
	catch (KubernetesClientException e) {
	    System.out.println(e.getMessage());
	}

	if (pods.isEmpty()) {
	    return Response.noContent().build();
	}

	return Response.ok(pods).build();
    }
}
