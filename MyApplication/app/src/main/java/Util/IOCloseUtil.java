package Util;

import java.io.Closeable;
import org.apache.commons.io.IOUtils;

public class IOCloseUtil {
	public static void closeSilently(Closeable closeable) {
		try {
			if (closeable!=null) {
				closeable.close();
			}
		} catch (Exception e) {
		}			
	}
	
	public static void closeSilently_unclose(Closeable closeable) {
		try {
			if (closeable!=null) {
				//closeable.close();
			}
		} catch (Exception e) {
		}			
	}
	
	public static void closeGoogle(Closeable closeable) {
		try {
			if (closeable!=null) {
				IOUtils.closeQuietly(closeable);
			}
		} catch (Exception e) {
		}	
	}
	
	public static void closeGoogle_unclose(Closeable closeable) {
		try {
			if (closeable!=null) {
				//IOUtils.closeQuietly(closeable);
			}
		} catch (Exception e) {
		}	
	}
}
