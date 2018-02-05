package LintCode.SystemDesign.ConsistentHashing_TinyUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implement a load balancer for web servers. It provide the following functionality:
 * 
 * Add a new server to the cluster => add(server_id). Remove a bad server from the cluster => remove(server_id). Pick a
 * server in the cluster randomly with equal probability => pick().
 */
public class LoadBalancer {

    private List<Integer> serverList = new ArrayList<>();
    private Random random = new Random();

    public LoadBalancer() {
        // do intialization if necessary
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        // write your code here
        if (!serverList.contains(server_id)) {
            serverList.add(server_id);
        }
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        // write your code here
        serverList.remove((Number) server_id);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        // write your code here
        return serverList.get(random.nextInt(serverList.size()));
    }
}