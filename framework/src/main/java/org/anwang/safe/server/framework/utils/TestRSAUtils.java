package org.anwang.safe.server.framework.utils;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

public class TestRSAUtils {

    public static void main(String[] args) throws Exception {

        /**
         * 公钥:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAUII7P12gJhz5xvMY8YRdVL7j5qbGWyAjez01uFT0+HJQvOCBFANSfPhihBFP+fMU0dE556+4HGjEKbY4hvdFS0CywO2GmycxlAxVwvm/kVdrpp9dr0bQ41xu2vtu4me1pvdMhh02JekLgHccJtVgwWlsDI3E2AIj1Vm2KLP3PwIDAQAB
         * 私钥:MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIBQgjs/XaAmHPnG8xjxhF1UvuPmpsZbICN7PTW4VPT4clC84IEUA1J8+GKEEU/58xTR0Tnnr7gcaMQptjiG90VLQLLA7YabJzGUDFXC+b+RV2umn12vRtDjXG7a+27iZ7Wm90yGHTYl6QuAdxwm1WDBaWwMjcTYAiPVWbYos/c/AgMBAAECgYAUHAn8/RZQRgjegHaS3y8ObXypPPnANoHX7bWPGL9/0PwheSh6Mke/AsTus6H+9b5J8gQnXg8k/JbSv3LGcyiHYorZWwm7LBwN7uttRbdV4K8Sc38X86JuwhsE2nyQn6Q9p5BYxpQ0qjz/JWXIJInpph/pSykHd3XgmfFIz3lSSQJBAN/OBtQx4AgtCqDmIOnl/EZrvzpD28AAuvUoZ2XW+SZnSfFmQHCRc9exwyDc3tfkg6pwsxINQDwd9YfbIHrBifsCQQCSxeWtf02Y9BHKHU24Fj2SbizSAtb75qPJnpuFjqkfp0Idc7o72vzxIZa59K1cctcoIXBYu9Kir+mdeu1Ll2iNAkEAk2thoiqMOLZ6lsufiJJ+n2Bf4SBLwuh4Pf50RWfaTAqjcUh6T+vvk3e7YY07mJgkhoOf4Ncrpa3SSr/zYAjWOwJAPEVHq5c7JjQTmguufomjfx3/Cb5gydBMMd8Pvih3CqiEXdPIBgxHEj3Yx0PSbMQ71h7lI4r9VBEovaJdD019gQJAZQsqU9r7O5BWrNm5f99LUUrv0pmoS8e8KzWSUdTyG3Ald/Gaq5UO4TZqeSoR0zhKOS0wq9STY/g/ShzZDAhecw==
         */
        String message = "";
        String pubKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAUII7P12gJhz5xvMY8YRdVL7j5qbGWyAjez01uFT0+HJQvOCBFANSfPhihBFP+fMU0dE556+4HGjEKbY4hvdFS0CywO2GmycxlAxVwvm/kVdrpp9dr0bQ41xu2vtu4me1pvdMhh02JekLgHccJtVgwWlsDI3E2AIj1Vm2KLP3PwIDAQAB";
        String priKey  = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIBQgjs/XaAmHPnG8xjxhF1UvuPmpsZbICN7PTW4VPT4clC84IEUA1J8+GKEEU/58xTR0Tnnr7gcaMQptjiG90VLQLLA7YabJzGUDFXC+b+RV2umn12vRtDjXG7a+27iZ7Wm90yGHTYl6QuAdxwm1WDBaWwMjcTYAiPVWbYos/c/AgMBAAECgYAUHAn8/RZQRgjegHaS3y8ObXypPPnANoHX7bWPGL9/0PwheSh6Mke/AsTus6H+9b5J8gQnXg8k/JbSv3LGcyiHYorZWwm7LBwN7uttRbdV4K8Sc38X86JuwhsE2nyQn6Q9p5BYxpQ0qjz/JWXIJInpph/pSykHd3XgmfFIz3lSSQJBAN/OBtQx4AgtCqDmIOnl/EZrvzpD28AAuvUoZ2XW+SZnSfFmQHCRc9exwyDc3tfkg6pwsxINQDwd9YfbIHrBifsCQQCSxeWtf02Y9BHKHU24Fj2SbizSAtb75qPJnpuFjqkfp0Idc7o72vzxIZa59K1cctcoIXBYu9Kir+mdeu1Ll2iNAkEAk2thoiqMOLZ6lsufiJJ+n2Bf4SBLwuh4Pf50RWfaTAqjcUh6T+vvk3e7YY07mJgkhoOf4Ncrpa3SSr/zYAjWOwJAPEVHq5c7JjQTmguufomjfx3/Cb5gydBMMd8Pvih3CqiEXdPIBgxHEj3Yx0PSbMQ71h7lI4r9VBEovaJdD019gQJAZQsqU9r7O5BWrNm5f99LUUrv0pmoS8e8KzWSUdTyG3Ald/Gaq5UO4TZqeSoR0zhKOS0wq9STY/g/ShzZDAhecw==";

        RSAPublicKey publicKey = RSAUtils.getPublicKey(pubKey);
        String encryptedMsg = RSAUtils.encryptByPublicKey(message , publicKey);
        System.out.println(encryptedMsg);


    }

}
