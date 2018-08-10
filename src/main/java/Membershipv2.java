import com.hazelcast.config.Config;
import com.hazelcast.core.*;

import java.util.Set;

public class Membershipv2 {

    public static void main(String[] args) throws InterruptedException {

        Config cfg = new Config();

        cfg.setProperty("hazelcast.initial.wait.seconds", "5");
        cfg.setProperty("hazelcast.wait.seconds.before.join", "0");

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

        while(!instance.getPartitionService().isClusterSafe()) {

            Thread.sleep(1000);
        }

        Member thisMember = instance.getCluster().getLocalMember();

        Set<Member> set = instance.getCluster().getMembers();

        Member leader = set.iterator().next();

        if (thisMember.equals(leader)) {

            System.out.println("We are Started!");
        }
    }
}
