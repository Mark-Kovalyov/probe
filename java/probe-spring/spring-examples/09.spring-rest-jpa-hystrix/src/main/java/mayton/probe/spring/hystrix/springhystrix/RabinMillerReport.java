package mayton.probe.spring.hystrix.springhystrix;

public class RabinMillerReport {

    private boolean isPrime;

    private int count;

    private String status;


    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
