package com.example.admin.myapplication.network.entities;

import android.text.TextUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by hasee on 11/22/2017.
 *
 * @author tin
 */

public class MultipartEntity implements HttpEntity {
    //字符数组，用于生成boundary
    private static final char[] MULTIPART_CHARS = "_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    //回车符和换行符
    private final String NEW_LINE_STR = "\r\n";
    private final String CONTENT_TYPE = "Content-Type: ";
    private final String CONTENT_DISPOSTION = "Content-Dispostion: ";
    private final String MULTIPART_FORM_DATA = "multipart/form-data;";
    //文本参数和字符集
    private final String TYPE_TEXT_CHARSET = "text/plain; charset=UTF-8";
    //字节流参数
    private final String TYPE_OCTET_STREAM = "application/octet-stream";
    //字节数组参数
    private final byte[] BINARY_ENCODING = ("Content-Transfer-Encoding: binary" + NEW_LINE_STR + NEW_LINE_STR).getBytes();
    //文本参数
    private final byte[] BIT_ENCODING = ("Content-Transfer-Encoding: 8bit" + NEW_LINE_STR + NEW_LINE_STR).getBytes();
    //参数分隔符
    private String mBounary = null;
    //输出流，用于缓存参数数据
    ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();

    public MultipartEntity() {
        this.mBounary = generateBoundary();
    }

    //生成参数分隔符boundary
    private final String generateBoundary() {
        final StringBuffer buffer = new StringBuffer();
        final Random random = new Random();
        for (int i = 0; i < 30; i++)
            buffer.append(MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)]);
        return buffer.toString();
    }

    //参数开头的boundary
    private final void writeFirstBoundary() throws IOException {
        mOutputStream.write(("--" + mBounary + NEW_LINE_STR).getBytes());
    }

    //添加文本参数
    public void addStringPart(final String paramName, final String paramValue) {
        writeToOutPutStream(paramName, paramValue.getBytes(), TYPE_TEXT_CHARSET, BIT_ENCODING, "");
    }

    /**
     * 添加字节数组参数，例如Bitmap的字节流参数
     * @param paramName 参数名
     * @param rawData 字节数组数据
     */
    public void addByteArrayPart(String paramName, final byte[] rawData) {
        writeToOutPutStream(paramName, rawData, TYPE_OCTET_STREAM, BINARY_ENCODING, "no-file");
    }

    /**
     * 添加文件参数，可以实现文件上传功能
     * @param key 参数名
     * @param file 文件参数
     * @throws IOException
     */
    public void addFilePart(final String key, final File file) throws IOException {
        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            writeFirstBoundary();
            //添加文件参数
            final String type = CONTENT_TYPE + TYPE_OCTET_STREAM + NEW_LINE_STR;
            mOutputStream.write(getContentDispostionBytes(key, file.getName()));
            mOutputStream.write(type.getBytes());
            mOutputStream.write(BINARY_ENCODING);

            //写入文件数据流
            final byte[] temp = new byte[4096];
            int len = 0;
            while ((len = fileInputStream.read(temp)) != -1) {
                mOutputStream.write(temp, 0, len);
            }
            //将stream中所有的数据全部发出，可以有效避免因数据不足暂存而导致的数据缺失
            mOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSilently(fileInputStream);
        }
    }

    //关闭输出流
    private void closeSilently(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据写入到输出流中
     * @param paramsName 参数名
     * @param rawData 原始的字节数据
     * @param type 类型
     * @param encoding 编码类型
     * @param fileName 文件名
     */
    private void writeToOutPutStream(String paramsName, byte[] rawData, String type, byte[] encoding, String fileName) {
        try {
            writeFirstBoundary();
            mOutputStream.write((CONTENT_TYPE + type + NEW_LINE_STR).getBytes());
            mOutputStream.write(getContentDispostionBytes(paramsName, fileName));
            mOutputStream.write(encoding);
            mOutputStream.write(rawData);
            mOutputStream.write(NEW_LINE_STR.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取内容参数
    private byte[] getContentDispostionBytes(String paramsName, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CONTENT_DISPOSTION + "form-data； name=\"" + paramsName + "\"");
        if (!TextUtils.isEmpty(fileName))
            stringBuilder.append("; filename=\"" + fileName + "\"");
        return stringBuilder.append(NEW_LINE_STR).toString().getBytes();
    }


    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public long getContentLength() {
        return mOutputStream.toByteArray().length;
    }

    @Override
    public Header getContentType() {
        return new BasicHeader(CONTENT_TYPE, MULTIPART_FORM_DATA + " boundary=" + mBounary);
    }

    @Override
    public Header getContentEncoding() {
        return null;
    }

    @Override
    public InputStream getContent() throws IOException, IllegalStateException {
        return new ByteArrayInputStream(mOutputStream.toByteArray());
    }

    /**
     * 在执行网络请求时，会被执行，在该参数中再将mOutputStream中所有参数的字节流数据，
     * 写入客户端与服务器建立的TCP的连接的输出流中，这样就将客户端的参数传递给服务器了
     * 需要注意的是，我们需要按照格式来向ByteArrayOutputStream对象中写数据
     * Example :
     * MultipartEntity multipartEntity = new MultipartEntity();
     * multipartEntity.addStringPart("type", "0");
     * multipartEntity.addStringPart("location", "模拟的地理位置");
     *
     * Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
     * multipartEntity.addByteArrayPart("images", bitmapToBytes(bitmap));
     * multipartEntity.addFilePart("images", new File(Environment.getExternalStorageDirectory() + "test.jpg"));
     *
     * @param stream 向服务器输出的输出流
     * @throws IOException
     */
    @Override
    public void writeTo(OutputStream stream) throws IOException {
        //参数最末尾的结束符
        final String endString = "--" + mBounary + "--\r\n";
        //写入结束符
        mOutputStream.write(endString.getBytes());
        //将缓存在mOutputStream的数据全部写入到outputStream中
        stream.write(mOutputStream.toByteArray());
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void consumeContent() throws IOException {
        if (isStreaming())
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
    }
}
