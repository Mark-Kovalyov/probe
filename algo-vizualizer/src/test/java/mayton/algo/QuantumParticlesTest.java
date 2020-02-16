package mayton.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuantumParticlesTest {

    @Test
    public void test() {
        QuantumParticles quantumParticles = new QuantumParticles();
        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(0));
        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(1));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(2));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(3));
        assertEquals(2, quantumParticles.nextQuantumFiveToThreeState(4));

        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(0));
        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(1));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(2));
        assertEquals(2, quantumParticles.nextQuantumFiveToThreeState(3));
        assertEquals(2, quantumParticles.nextQuantumFiveToThreeState(4));

        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(0));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(1));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(2));
        assertEquals(2, quantumParticles.nextQuantumFiveToThreeState(3));
        assertEquals(2, quantumParticles.nextQuantumFiveToThreeState(4));

        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(0));
        assertEquals(0, quantumParticles.nextQuantumFiveToThreeState(1));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(2));
        assertEquals(1, quantumParticles.nextQuantumFiveToThreeState(3));
        assertEquals(2, quantumParticles.nextQuantumFiveToThreeState(4));
    }

}
