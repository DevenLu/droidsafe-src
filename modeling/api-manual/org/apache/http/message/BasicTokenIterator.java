package org.apache.http.message;

import org.apache.http.TokenIterator;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSModeled;

public class BasicTokenIterator implements TokenIterator {

	@DSModeled(DSC.SAFE)
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return new Object();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@DSModeled(DSC.SAFE)
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String nextToken() {
		// TODO Auto-generated method stub
		return null;
	}

}