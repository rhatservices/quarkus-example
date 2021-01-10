package at.stderr;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;

@Path("/pods")
public class podResource {
	private final KubernetesClient k8sClient;
	

	public podResource(KubernetesClient k8sClient) {
		this.k8sClient = k8sClient;
	}
	
    @GET
    @Path("{namespace}")
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Pod> pods(@PathParam("namespace") String namespace) {
    		List<Pod> pods = k8sClient.pods().inNamespace(namespace).list().getItems();
    		//System.out.println(pods);
    		return pods;
    }
}