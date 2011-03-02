package org.handwerkszeug.dns.record;

import java.util.Arrays;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.nls.Messages;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.3.10. NULL RDATA format (EXPERIMENTAL)
 * 
 * @author taichi
 * 
 */
public class NULLRecord extends AbstractRecord {

	/**
	 * Anything at all may be in the RDATA field so long as it is 65535 octets
	 * or less.
	 */
	protected byte[] anything;

	public NULLRecord() {
		super(RRType.NULL);
	}

	public NULLRecord(NULLRecord from) {
		super(from);
		byte[] ary = from.anything();
		if (ary != null) {
			this.anything(Arrays.copyOf(ary, ary.length));
		}
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.anything = new byte[rdlength()];
		buffer.readBytes(this.anything);
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		buffer.writeBytes(anything());
	}

	@Override
	protected ResourceRecord newInstance() {
		return new NULLRecord(this);
	}

	public byte[] anything() {
		return this.anything;
	}

	public void anything(byte[] data) {
		if (65535 < data.length) {
			throw new IllegalArgumentException(String.format(
					Messages.DataMustBe65535orLess, data.length));
		}
		this.anything = data;
	}
}
