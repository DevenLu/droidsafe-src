package org.apache.harmony.security.fortress;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.security.Provider;
import java.util.List;

public interface SecurityAccess {
    
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    public void renumProviders();
    
    public List<String> getAliases(Provider.Service s);
    
    public Provider.Service getService(Provider p, String type);
}