package com.dali.security.Service;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.TimeProviderException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Service
@Slf4j
public class TwoFactorAuthenticationService {
    public String generateNewSecret()
    {
        return  new DefaultSecretGenerator().generate();
    }
    public String generateQRCode(String secret)
    {
        QrData data=new QrData.Builder()
                .label("Esprit ")
                .secret(secret)
                .issuer("Intership Management")
                .algorithm(HashingAlgorithm.SHA1)
                .digits(6)
                .period(30)
                .build();
        QrGenerator generator= new ZxingPngQrGenerator();
        byte [] imageData=new byte[0];
        try {
            imageData = generator.generate(data);
        } catch (Exception e) {
            log.error("Error while generating QR code",e);
        }
        return getDataUriForImage(imageData,generator.getImageMimeType());
    }
    public boolean isOtpValid(String secret, String code)
    {
        TimeProvider timeProvider=new SystemTimeProvider();
        CodeGenerator codeGenerator=new DefaultCodeGenerator();
        CodeVerifier verifier=new DefaultCodeVerifier(codeGenerator,timeProvider);
        return verifier.isValidCode(secret,code);
    }
    public boolean isOtpNotValid(String secret, String code)
    {
        return  !isOtpValid(secret,code);
    }
}
