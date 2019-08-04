package probe.mayton.orc;

public class Utils {

    public static int ip(String ips) {
        int[] ip = new int[4];
        String[] parts = ips.split("\\.");
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(parts[i]);
        }
        return  ip[3] << 24 |
                ip[2] << 16 |
                ip[1] << 8 |
                ip[0] ;
    }

}
