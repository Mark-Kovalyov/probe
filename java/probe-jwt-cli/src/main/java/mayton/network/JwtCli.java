package mayton.network;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class JwtCli {

    public static void main(String[] args) throws IOException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse((IOUtils.toString(new FileInputStream(args[0]), StandardCharsets.UTF_8)));
        System.out.println(signedJWT.getJWTClaimsSet());
    }

}
