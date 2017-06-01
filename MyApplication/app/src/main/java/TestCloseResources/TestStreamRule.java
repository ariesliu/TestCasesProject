package TestCloseResources;
import org.apache.commons.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipFile;

import Util.IOCloseUtil;
import Util.QHRecyleUtils;

/*
 * 资源对象没有关闭。
 * 请确保输入输出流创建的对象在使用完成后进行了妥善的关闭处理。
 * 例如java.io.InputStream，java.io.OutputStream，
 * java.io.Reader，java.io.Writer，
 * java.util.zip.ZipFile，java.net.socket等类创建出的对象。
 * 
 * */
public class TestStreamRule {

	/**
	 * 不关闭
	 * @throws IOException
	 */
	
	public void test_01_BUG() throws IOException {
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.write("11111");		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 从Java1.7开始，支持了try-with-resources写法
	 * @throws IOException
	 */

	public void test_02_NOBUG() throws IOException {
		try (
				FileOutputStream fos = new FileOutputStream("e:/a.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8")
			  )
		{
			osw.append("11111");		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 跨文件自定义close方法关闭
	 */
	public void test_03_NOBUG(){
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("22222");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			IOCloseUtil.closeSilently(osw);
		}
	}
	
	/**
	 * 跨文件自定义close方法关闭
	 * 调用方法中的关闭操作注释掉，实际没有关闭
	 */
	public void test_04_BUG(){
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("22222");
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			IOCloseUtil.closeSilently_unclose(osw);
		}
	}
	
	/**
	 * 第三方库方法关闭
	 * @throws IOException 
	 */
	public void test_05_NOBUG() throws IOException{
		FileOutputStream fos;
		OutputStreamWriter osw=null;
	     try {
	       // ... code which does something with the stream ...
	    	 fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("33333");
	     } finally {
	    	 IOUtils.closeQuietly(osw);
	     }
	}
	
	
	/**
	 * 当前文件自定义方法关闭
	 */
	public void test_06_NOBUG(){
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("44444");
			closeMethod(osw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void closeMethod(OutputStreamWriter osw){
		try {
			if(osw != null){
				osw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 当前文件自定义方法关闭
	 * 调用方法中的关闭操作注释掉，实际没有关闭
	 */
	
	public void test_07_BUG(){
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("44444");
			closeMethod_unclose(osw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void closeMethod_unclose(OutputStreamWriter osw){
		try {
			if(osw != null){
				//osw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 跨多次文件的第三方库关闭方法
	 */
	public void test_08_NOBUG(){	
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("55555");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			IOCloseUtil.closeGoogle(osw);
		}
	}
	
	/**
	 * 跨多次文件的第三方库关闭方法
	 * 调用方法中的关闭操作注释掉，实际没有关闭
	 */
	public void test_09_BUG(){	
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("55555");
			IOCloseUtil.closeGoogle_unclose(osw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 先跨自定义方法，再跨类关闭
	 * 
	 */
	public void test_10_NOBUG(){	
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("55555");
			closeMethod_close_util(osw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void closeMethod_close_util(OutputStreamWriter osw){
		try {
			if(osw != null){
				IOCloseUtil.closeSilently(osw);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 先跨自定义方法，再跨类，但是实际没有关闭
	 * 
	 */
	public void test_11_BUG(){	
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("55555");
			closeMethod_unclose_util(osw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void closeMethod_unclose_util(OutputStreamWriter osw){
		try {
			if(osw != null){
				IOCloseUtil.closeSilently_unclose(osw);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 先跨自定义方法，再跨类，再调用第三方类进行关闭
	 *
	 */
	public void test_12_NOBUG(){	
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("55555");
			closeMethod_close_util1(osw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void closeMethod_close_util1(OutputStreamWriter osw){
		try {
			if(osw != null){
				IOCloseUtil.closeGoogle(osw);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	/**
	 * finally close()关闭
	 * @throws IOException
	 */
	public void test_13_NOBUG() throws IOException {
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("11111");		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			osw.close();
		}
	}
	
	/**
	 * 如果有资源对象在初始化时，初始化的参数实际是另外一个构造函数，
	 * 那么如果GZIPOutputStream初始化时出现异常，
	 * FileOutputStream的资源对象就无法被关闭了。
	 * 相关资源对象包括：
	 * ObjectInputStream , ObjectOutputStream, 
	 * PipedInputStream, PipedOutputStream, 
	 * PipedReader, PipedWriter,
	 * JarInputStream, JarOutputStream,
	 * GZIPInputStream, GZIPOutputStream ,
	 * ZipFile all throw IOException
	 * PrintStream throws UnsupportedEncodingException
	 */
	public void test_14_BUG() throws IOException {
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(new File("e:/a.txt")));
		
	}
	
	/**
	 * 资源对象在套接使用时，只需要手动关闭最后套接的对象
	 * 
	 */
	public void test_15_NOBUG() throws IOException {
		FileOutputStream fileOutputStream = null;
	    BufferedOutputStream bufferedOutputStream=null;
	    DataOutputStream out=null;
	    byte[] data1 = "测试".getBytes("GB2312");
	    byte[] data2 = "这个".getBytes("GB2312");
	    byte[] data3 = "案例".getBytes("GB2312");
	    try {
	        fileOutputStream = new FileOutputStream("E:/a.txt");
	        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
	        out = new DataOutputStream(bufferedOutputStream);
	        //out.close();//在该位置关闭时，A.txt没有内容
	        fileOutputStream.write(data1);
	        bufferedOutputStream.write(data2);
	        out.write(data3);
	    } catch (Exception e) {
	        // TODO: handle exception
	    } finally {
	        if (out!=null) {
	            out.close();
	        }
	    }
	}
	
	/**
	 * ByteArrayInputStream等不需要检查关闭的资源对象
	 * ByteArrayInputStream、ByteArrayOutputStream、
	 * StringBufferInputStream、CharArrayWriter、和StringWriter
	 */
	public void test_16_NOBUG() throws IOException {
		FileOutputStream fileOutputStream = null;
	    ByteArrayOutputStream bufferedOutputStream=null;
	    DataOutputStream out=null;
	    byte[] data1 = "测试".getBytes("GB2312");
	    byte[] data2 = "这个".getBytes("GB2312");
	    byte[] data3 = "案例".getBytes("GB2312");
	    try {
	        fileOutputStream = new FileOutputStream("E:\\A.txt");
	        bufferedOutputStream = new ByteArrayOutputStream();
	        fileOutputStream.write(data1);
	        bufferedOutputStream.write(data2);
	    } catch (Exception e) {
	        // TODO: handle exception
	    } finally {
			fileOutputStream.close();
	    }
	}

	
	/**
	 * Socket关闭检查，没有关闭
	 */
	public void test_17_BUG() throws IOException {
		Socket socket = new Socket("127.0.0.1", 8001);    
	}

	/**
	 * 通过Socket获取的getInputStream和getOutputStream不需要手动关闭。
	 * 关闭Socket对象会自动关闭通过Socket获取的资源对象。
	 */
	public void test_18_NOBUG() throws IOException {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 8001);
			InputStream inputStream = socket.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			socket.close();
		}
	}

	/**
	 * 通过Socket获取的getInputStream和getOutputStream不需要手动关闭。
	 * 跨类关闭Socket对象会自动关闭通过Socket获取的资源对象。
	 */
	public void test_19_NOBUG() throws IOException {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 8001);
			InputStream inputStream = socket.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			QHRecyleUtils.safeClose(socket);
		}
	}

	
	/**
	 * ZipFile关闭检查，没有关闭
	 */
	public void test_20_BUG() throws IOException {
		ZipFile zf = new ZipFile("D:\\ztree.zip");  
	}
	/**
	 * ZipFile关闭检查
	 */
	public void test_21_NOBUG() throws IOException {
		ZipFile zf= null;
		try {
			zf = new ZipFile("D:\\ztree.zip"); 
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			zf.close();
		}	
	}
	
	/**
	 * 复杂写法
	 */
	public void test_22_NOBUG() throws IOException {
		FileOutputStream fos;
		OutputStreamWriter osw=null;
		try {
			fos = new FileOutputStream("e:/a.txt");
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.append("22222");
			try {
				osw.write("55555");
				try {
					osw.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
				try {
					osw.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}	
		} catch (Exception e) {
			// TODO: handle exception
			try {
				osw.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
}