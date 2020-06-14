package cn.lamamama.foi.read;

import cn.lamamama.foi.IRead;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class AbstractRead<T> implements IRead<T> {

    public void load(Handler<T> handler, File file) throws IOException {
        this.load(handler, new FileInputStream(file));
    }

    public List<T> all(InputStream inputStream) throws IOException {
        List<T> handlerResults = Lists.newArrayList();
        this.load((row, t) -> handlerResults.add(t), inputStream);
        return handlerResults;
    }

    public List<T> all(File file) throws IOException {
        return this.all(new FileInputStream(file));
    }
}
