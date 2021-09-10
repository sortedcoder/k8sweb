package k8sweb;

import java.util.List;
import java.util.stream.Collectors;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import k8sweb.k8s.K8S;

@Controller("/hello")
public class MainController {

    @Inject
    K8S k8s;

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<String> index() {

        CoreV1Api api = new CoreV1Api();
        V1PodList list;
        List<String> namespaces = null;
        try {
            list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
            namespaces = list.getItems().stream().map(i->i.getMetadata().getName()).collect(Collectors.toList());
//                    .stream()
//                            .map(n->n.getMetadata().getName())
//                            .collect(Collectors.joining(","));
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return namespaces;
    }
}
