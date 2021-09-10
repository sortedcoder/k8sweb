package k8sweb.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.Config;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class K8S {
    public  K8S() {
        System.out.println("Initializing k8s configuration");
        ApiClient client;
        try {
            client = Config.defaultClient();
            Configuration.setDefaultApiClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
