package mayton.qrcode;

import com.google.zxing.EncodeHintType;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class QRCodeGen {

    public static void main(String[] args) throws Exception{

        QRCode.from(IOUtils.toString(new FileInputStream(args[0]),"utf-8"))
                .to(ImageType.PNG)
                .withCharset("UTF-8")
                .withSize(320,320)
                .withHint(EncodeHintType.CHARACTER_SET, "UTF-8")
                .writeTo(new FileOutputStream(args[1]));

    }

}
