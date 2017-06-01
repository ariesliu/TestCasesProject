package com.qtest.ruletestcases
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
/**
 * Created by yuanwei3-iri on 2017/5/24.
 */
@Throws(IOException::class)
fun test_01_BUG() {
    val fos:FileOutputStream
    val osw:OutputStreamWriter
    try
    {
        fos = FileOutputStream("e:/a.txt")
        osw = OutputStreamWriter(fos, "UTF-8")
        osw.write("11111")
    }
    catch (e:Exception) {
        // TODO: handle exception
    }
}