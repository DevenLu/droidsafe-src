package org.apache.http.client.methods;

// Droidsafe Imports
import droidsafe.annotations.*;
import droidsafe.helpers.*;

import droidsafe.runtime.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class HttpRequestBase extends AbstractHttpMessage implements HttpUriRequest, AbortableHttpRequest, Cloneable {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.197 -0400", hash_original_field = "10FCE3ACD248786FEA19D4FD8EF82936", hash_generated_field = "44B412448D230F2FF1F66097B168F5A5")

    private Lock abortLock;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.197 -0400", hash_original_field = "DFB7069BFC6E0064A6C667626ECA07B4", hash_generated_field = "7C8E769DDEE22768B70313CCC815CA01")

    private boolean aborted;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.198 -0400", hash_original_field = "9305B73D359BD06734FEE0B3638079E1", hash_generated_field = "DDFAECCF21F90F28142F5C866534A5C3")

    @DSVAModeled
    private URI uri;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.198 -0400", hash_original_field = "FAFFE38284DD03831134F3DCA5636A97", hash_generated_field = "9BD569E8B5A75FB995CED8041D010CC7")

    private ClientConnectionRequest connRequest;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.201 -0400", hash_original_field = "1D2148D46F6E571FED3D27C9091F509B", hash_generated_field = "67B34A5893107F972D9958340D6670E2")

    private ConnectionReleaseTrigger releaseTrigger;
    
    @DSModeled(DSC.SPEC)
	public HttpRequestBase() { 
		super();
	}

    
    @DSModeled(DSC.SAFE)
    public abstract String getMethod();

    
    @DSModeled(DSC.SAFE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.203 -0400", hash_original_method = "E43BB65B6EAF4E56B5A8181704221BD7", hash_generated_method = "FC3440A02D7955CD000653C92120C472")
    public ProtocolVersion getProtocolVersion() {
ProtocolVersion varEDB6822BC4AFEFEDB387B740DB02A284_1354538771 =         HttpProtocolParams.getVersion(getParams());
        varEDB6822BC4AFEFEDB387B740DB02A284_1354538771.addTaint(taint);
        return varEDB6822BC4AFEFEDB387B740DB02A284_1354538771;
        // ---------- Original Method ----------
        //return HttpProtocolParams.getVersion(getParams());
    }

    
    @DSModeled(DSC.SPEC)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.205 -0400", hash_original_method = "548D933490D1B58733888D02043A11B0", hash_generated_method = "33D5B511ABD46434CA9565C3EE794880")
    public URI getURI() {
URI varF8A43570A6D9CE873AAA24BBE28B6265_1820848507 =         this.uri;
        varF8A43570A6D9CE873AAA24BBE28B6265_1820848507.addTaint(taint);
        return varF8A43570A6D9CE873AAA24BBE28B6265_1820848507;
        // ---------- Original Method ----------
        //return this.uri;
    }

    
    @DSModeled(DSC.SAFE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.207 -0400", hash_original_method = "93E6AFEE57EFDA4898CAE2DF347D0D02", hash_generated_method = "943FBA157A05E91D6F6D094FB742AD79")
    public RequestLine getRequestLine() {
        String method = getMethod();
        ProtocolVersion ver = getProtocolVersion();
        URI uri = getURI();
        String uritext = null;
        if(uri != null)        
        {
            uritext = uri.toASCIIString();
        } //End block
        if(uritext == null || uritext.length() == 0)        
        {
            uritext = "/";
        } //End block
RequestLine var61D16D1DBAF633F6BF5B3B3F6C9131D9_1565140925 =         new BasicRequestLine(method, uritext, ver);
        var61D16D1DBAF633F6BF5B3B3F6C9131D9_1565140925.addTaint(taint);
        return var61D16D1DBAF633F6BF5B3B3F6C9131D9_1565140925;
        // ---------- Original Method ----------
        //String method = getMethod();
        //ProtocolVersion ver = getProtocolVersion();
        //URI uri = getURI();
        //String uritext = null;
        //if (uri != null) {
            //uritext = uri.toASCIIString();
        //}
        //if (uritext == null || uritext.length() == 0) {
            //uritext = "/";
        //}
        //return new BasicRequestLine(method, uritext, ver);
    }

    
    @DSModeled(DSC.SPEC)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.208 -0400", hash_original_method = "3320A765B2CE48350AC27D9599EAC026", hash_generated_method = "C72876729647E8A3A80A7FBC268F56B2")
    public void setURI(final URI uri) {
        this.uri = uri;
        // ---------- Original Method ----------
        //this.uri = uri;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.212 -0400", hash_original_method = "AD26D5D661F235A4EA5BC460121EF4B1", hash_generated_method = "BA2B5F4D7F1226580522E87678994B7E")
    public void setConnectionRequest(final ClientConnectionRequest connRequest) throws IOException {
        this.abortLock.lock();
        try 
        {
            if(this.aborted)            
            {
                IOException var9ED6BC09125D6400798327C9041CA613_908359043 = new IOException("Request already aborted");
                var9ED6BC09125D6400798327C9041CA613_908359043.addTaint(taint);
                throw var9ED6BC09125D6400798327C9041CA613_908359043;
            } //End block
            this.releaseTrigger = null;
            this.connRequest = connRequest;
        } //End block
        finally 
        {
            this.abortLock.unlock();
        } //End block
        // ---------- Original Method ----------
        //this.abortLock.lock();
        //try {
            //if (this.aborted) {
                //throw new IOException("Request already aborted");
            //}
            //this.releaseTrigger = null;
            //this.connRequest = connRequest;
        //} finally {
            //this.abortLock.unlock();
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.213 -0400", hash_original_method = "F06B06B54565935399EE76A02EF85A84", hash_generated_method = "DEAEDE606B2BB59E29D8FE6B3BB960D9")
    public void setReleaseTrigger(final ConnectionReleaseTrigger releaseTrigger) throws IOException {
        this.abortLock.lock();
        try 
        {
            if(this.aborted)            
            {
                IOException var9ED6BC09125D6400798327C9041CA613_908050382 = new IOException("Request already aborted");
                var9ED6BC09125D6400798327C9041CA613_908050382.addTaint(taint);
                throw var9ED6BC09125D6400798327C9041CA613_908050382;
            } //End block
            this.connRequest = null;
            this.releaseTrigger = releaseTrigger;
        } //End block
        finally 
        {
            this.abortLock.unlock();
        } //End block
        // ---------- Original Method ----------
        //this.abortLock.lock();
        //try {
            //if (this.aborted) {
                //throw new IOException("Request already aborted");
            //}
            //this.connRequest = null;
            //this.releaseTrigger = releaseTrigger;
        //} finally {
            //this.abortLock.unlock();
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.217 -0400", hash_original_method = "DAE61D1D90E50FDB083EDE1BB1F7240A", hash_generated_method = "0F131ABFC7B71634E8AE05B4AC294115")
    public void abort() {
        ClientConnectionRequest localRequest;
        ConnectionReleaseTrigger localTrigger;
        this.abortLock.lock();
        try 
        {
            if(this.aborted)            
            {
                return;
            } //End block
            this.aborted = true;
            localRequest = connRequest;
            localTrigger = releaseTrigger;
        } //End block
        finally 
        {
            this.abortLock.unlock();
        } //End block
        if(localRequest != null)        
        {
            localRequest.abortRequest();
        } //End block
        if(localTrigger != null)        
        {
            try 
            {
                localTrigger.abortConnection();
            } //End block
            catch (IOException ex)
            {
            } //End block
        } //End block
        // ---------- Original Method ----------
        //ClientConnectionRequest localRequest;
        //ConnectionReleaseTrigger localTrigger;
        //this.abortLock.lock();
        //try {
            //if (this.aborted) {
                //return;
            //}            
            //this.aborted = true;
            //localRequest = connRequest;
            //localTrigger = releaseTrigger;
        //} finally {
            //this.abortLock.unlock();
        //}
        //if (localRequest != null) {
            //localRequest.abortRequest();
        //}
        //if (localTrigger != null) {
            //try {
                //localTrigger.abortConnection();
            //} catch (IOException ex) {
            //}
        //}
    }

    
    @DSModeled(DSC.SAFE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.218 -0400", hash_original_method = "2E6315A95F3ECC29CFD4A6E95CF920AD", hash_generated_method = "7610B9CCAE5C4D39E2CFC149B8318031")
    public boolean isAborted() {
        boolean var37D13E93374D4F6A174832DCC5E644B0_1760726006 = (this.aborted);
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_868001697 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_868001697;
        // ---------- Original Method ----------
        //return this.aborted;
    }

    
    @DSModeled(DSC.SAFE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:02.224 -0400", hash_original_method = "7E78C92898A9406CD028D56B8CBA9D93", hash_generated_method = "AF2B1D224B33A90EC3509BC6FE887139")
    @Override
    public Object clone() throws CloneNotSupportedException {
        HttpRequestBase clone = (HttpRequestBase) super.clone();
        clone.abortLock = new ReentrantLock();
        clone.aborted = false;
        clone.releaseTrigger = null;
        clone.connRequest = null;
        clone.headergroup = (HeaderGroup) CloneUtils.clone(this.headergroup);
        clone.params = (HttpParams) CloneUtils.clone(this.params);
Object var3DE52045BFD3C1BF3742F994ED6139AD_927745806 =         clone;
        var3DE52045BFD3C1BF3742F994ED6139AD_927745806.addTaint(taint);
        return var3DE52045BFD3C1BF3742F994ED6139AD_927745806;
        // ---------- Original Method ----------
        //HttpRequestBase clone = (HttpRequestBase) super.clone();
        //clone.abortLock = new ReentrantLock();
        //clone.aborted = false;
        //clone.releaseTrigger = null;
        //clone.connRequest = null;
        //clone.headergroup = (HeaderGroup) CloneUtils.clone(this.headergroup);
        //clone.params = (HttpParams) CloneUtils.clone(this.params);
        //return clone;
    }

    
}

