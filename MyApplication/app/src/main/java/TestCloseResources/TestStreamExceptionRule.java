package TestCloseResources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TestStreamExceptionRule {


	/**
	 * 当有多个资源需要关闭时，如果第一个资源fis.close()关闭时出现异常，
	 * 那么后面的资源fos.close()关闭会被跳过。
	 * @throws IOException
	 */

	public void test_01_BUG() throws IOException {
		FileInputStream fis = null;
	    FileOutputStream fos = null;
	    try {
	      fis = new FileInputStream(new File("e:/a.txt"));
	      fos = new FileOutputStream(new File("e:/a.txt"));
	      fos.write(fis.read());
	    } finally {
	      if (fis!=null)  fis.close();
	      if (fos!=null) fos.close();
	    }
	}
	
	/**
	 * 当一些资源对象需要被return时，是不需要关闭的，
	 * 但是如果处理不妥当，比如stream.write(7)出现异常，那么stream对象就无法关闭。
	 */

	public void test_02() throws IOException {
		File reportDirectory = new File("e:/a.txt");
		String fileName="";
		GetNewStream_BUG(reportDirectory, fileName);
	}
	
	private OutputStream GetNewStream_BUG(File reportDirectory, String fileName) throws IOException {
		// TODO 自动生成的方法存根
		File file = new File(reportDirectory, fileName);
	    OutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	    stream.write(7);
	    return stream;
	}

	/**
	 * 当一些资源对象需要被return时，是不需要关闭的，
	 * 这条是正确写法。
	 * @return 
	 */

	public void test_03() throws IOException {
		File reportDirectory = new File("e:/a.txt");
		String fileName="";
		GetNewStream_NOBUG(reportDirectory, fileName);
		
	}

	private OutputStream GetNewStream_NOBUG(File reportDirectory, String fileName) throws FileNotFoundException {
		// TODO 自动生成的方法存根
		File file = new File(reportDirectory, fileName);
	    OutputStream stream = new FileOutputStream(file);
	    return stream;
	}
	
}