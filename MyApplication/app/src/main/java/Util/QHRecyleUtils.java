package Util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

/**
 * 资源回收工具类
 */
public final class QHRecyleUtils {
    /**
     * Cursor类型
     *
     * @param cursor 要回收的资源
     */
    public static void safeClose(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    /**
     * 回收Reader类型
     *
     * @param reader 要回收的资源
     */
    public static void safeClose(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 回收SQLiteDatabase类型
     *
     * @param database 要回收的资源
     */
    public static void safeClose(SQLiteDatabase database) {
        if (database != null) {
            database.close();
        }
    }

    /**
     * 回收Writer类型
     *
     * @param writer 要回收的资源
     */
    public static void safeClose(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 回收InputStream类型
     *
     * @param inputStream 要回收的资源
     */
    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 回收OutputStream类型
     * @param outputStream 要回收的资源
     */
    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 回收Socket类型
     * @param socket 要回收的资源
     */
    public static void safeClose(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * RandomAccessFile
     *
     * @param accessFile 要回收的资源
     */
    public static void safeClose(RandomAccessFile accessFile) {
        if (accessFile != null) {
            try {
                accessFile.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 回收Process类型
     *
     * @param process 要回收的资源
     */
//    public static void destroy(Process process) {
//        if (process != null) {
//            QHProcessUtils.kill(process);
//        }
//    }

    /**
     * 回收Map类型
     *
     * @param map 要回收的资源
     */
    public static void clear(Map<?, ?> map) {
        if (map != null) {
            map.clear();
        }
    }

    /**
     * 回收Collection类型
     *
     * @param collection 要回收的资源
     */
    public static void clear(Collection<?> collection) {
        if (collection != null) {
            collection.clear();
        }
    }
}
