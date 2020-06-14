package cn.lamamama.foi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IRead<T> {

    /**
     * 流式读取，适合超大文件读取
     *
     * @param handler
     * @param inputStream
     */
    void load(Handler<T> handler, InputStream inputStream) throws IOException;

    /**
     * 流式读取，适合超大文件读取
     *
     * @param handler
     * @param file
     */
    void load(Handler<T> handler, File file) throws IOException;

    /**
     * 读取所有，适合小文件
     *
     * @param inputStream
     */
    List<T> all(InputStream inputStream) throws IOException;

    /**
     * 读取所有，适合小文件
     *
     * @param file
     */
    List<T> all(File file) throws IOException;

    interface Handler<T> {

        void handle(int row, T t);
    }
}