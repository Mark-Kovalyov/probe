package mayton.hadoop;

import com.googlecode.concurrenttrees.common.KeyValuePair;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.NodeFactory;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

import java.io.*;
import java.util.Iterator;

import static java.lang.Math.*;

public class BloomExp {

    static final double LN2 = log(2.0);

    static String rev(String line) {
        StringBuilder sb = new StringBuilder(line.length());
        int len = line.length();
        for (int i = 0; i < len; i++) {
            sb.append(line.charAt(len - i - 1));
        }
        return sb.toString();
    }

    static <T> void println(T o){
        System.out.println(o);
    }

    static PrintStream printf(String format, Object ...args) {
        return System.out.printf(format,args);
    }

    static int getOptimalK(long m, long n) {
        double M = (double) m;
        double N = (double) n;
        return (int) (ceil(M / N) * LN2); // M / N * 0.7
    }

    static int getOptimalM(long n, double p) {
        // TODO: Check
        return - (int)((n * log(p)) /  (LN2 * LN2));
    }

    static double getFalsePositiveProbability(long m, long n) {
        int k = getOptimalK(m, n);
        double M = (double) m;
        double N = (double) n;
        double K = (double) k;
        double func = exp(-K * N / M);
        return pow(1.0 - func, K);
    }

    /**
     *
     * @param falsePositiveProbability [0.0 .. 1.0], it's should be around 0.95 .. 0.99999
     * @param n amount of keys in filter
     * @return optimal K
     */
    static int getOptimalK(double falsePositiveProbability, long n){
        // TODO: Complete
        throw new RuntimeException("Not implemented yet");
    }

    static long getOptimalM(double falsePositiveProbability, long n){
        // TODO: Complete
        throw new RuntimeException("Not implemented yet");
    }

    static Pair<Integer,Long> getOptimalMK(){
        return new MutablePair<>(null, null);
    }

    public static void printTableByKeysAndProb(int n, double prob){
        println("");
    }

    public static void generateTable(int n){
        // k = (m/n) ln 2

        long step = 2048;

        long mBegin = 1024;
        long mEnd = 400L * 1024 * 1024;

        long msize = mBegin;

        while (msize < mEnd) {

            long m = msize * 8;

            if (getOptimalK(m, n) >= 1) {
                System.out.printf("n = %d keys , bloomSize = %s ( %d bits ) , k = %d ; P = %.8f \n",
                        n,
                        FileUtils.byteCountToDisplaySize(msize),
                        m,
                        getOptimalK(m, n),
                        getFalsePositiveProbability(m, n));

            }

            msize = (long) (msize * 1.2);
        }
    }

    public static void probeBloom(String path, String file) throws IOException {

        int n = 88_405_430;

        generateTable(n);

        System.exit(0);

        // 1 649 813 008 bits = 206 226 626
        BloomFilter bloomFilter = new BloomFilter(1_649_813_008, 13, Hash.MURMUR_HASH);

        PrintWriter pw = new PrintWriter(path + "/hashkiller-dict-potential-duplicates.txt");

        BufferedReader br = new BufferedReader(new FileReader(path + "/"+ file));

        String line;
        long cnt = 0;
        long duplicates = 0;
        while ((line = br.readLine()) != null) {
            Key key = new Key(line.getBytes("utf-8"));
            if (bloomFilter.membershipTest(key)) {
                duplicates++;
                //println("DUP: "+line);
                pw.println(line);
            }
            bloomFilter.add(key);
            cnt++;
            if (cnt % (1024L * 1024) == 0) {
                println(cnt);
            }
        }

        pw.close();

        println("Lines:      " + cnt);
        println("Duplicates: " + duplicates);

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(path + "/hashkiller-dict-bloom.dat"));

        bloomFilter.write(dos);

        dos.close();

        bloomFilter = null;

        DataInput di = new DataInputStream(new FileInputStream(path + "/hashkiller-dict-bloom.dat"));

        BloomFilter bloomFilter2 = new BloomFilter();

        bloomFilter2.readFields(di);

        println("Vectorsize = "+bloomFilter.getVectorSize());

    }

    public static void probeRadix(String path, String file) throws IOException {
        RadixTree<String> radixTree = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());

        BufferedReader br = new BufferedReader(new FileReader(path + "/"+ file));

        String line;
        long cnt = 0;
        long duplicates = 0;

        while ((line = br.readLine()) != null) {
            radixTree.putIfAbsent(line,"");
            cnt++;
            if (cnt % (1024L * 1024) == 0) {
                println(cnt);
            }
        }

        br.close();

        Iterable<KeyValuePair<String>> iterable = radixTree.getKeyValuePairsForClosestKeys("");

        Iterator<KeyValuePair<String>> iterator = iterable.iterator();

        while (iterator.hasNext()){

        }
    }

    public static void main(String[] args) throws Exception {

        probeBloom("c:/db","hashkiller-dict.txt");

        //probeRadix("c:/db","hashkiller-dict-emails.txt");

    }
}
