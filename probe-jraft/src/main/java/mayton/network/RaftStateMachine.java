package mayton.network;

import static mayton.network.RaftNodeStates.*;

public class RaftStateMachine {

    private RaftNodeStates state = FOLLOWER;

    public void onStartsUp() {
        state = FOLLOWER;
    }

    public void onStartsElection() {
        state = CANDIDATE;
    }

    public void onNewElection() {
        state = CANDIDATE;
    }

    public void onReceiveVotesFromMajorityOfServers() {
        state = LEADER;
    }


}
