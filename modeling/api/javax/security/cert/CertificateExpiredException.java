package javax.security.cert;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class CertificateExpiredException extends CertificateException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:53.764 -0500", hash_original_field = "5455EBFA3E2BA07D0B1665C466D0096F", hash_generated_field = "30011E046BDF8765F54F0D4CAE6C98CF")

    private static final long serialVersionUID = 5091601212177261883L;

    /**
     * Creates a new {@code CertificateExpiredException} with the specified
     * message.
     *
     * @param msg
     *            the detail message for this exception
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:53.766 -0500", hash_original_method = "695ACDE1E8C2F900ABF7FF7E73A29503", hash_generated_method = "C0E1DC4CDD24057AEE6812CE1BFC8C71")
    
public CertificateExpiredException(String msg) {
        super(msg);
    }

    /**
     * Creates a new {@code CertificateExpiredException}.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:53.769 -0500", hash_original_method = "308175145F4FC5BAB795337D4AA9A477", hash_generated_method = "E0A3C42420DFAB929F4467A0617FE06B")
    
public CertificateExpiredException() {
    }
}
