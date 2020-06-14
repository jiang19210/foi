package cn.lamamama.foi.read;

import cn.lamamama.foi.handler.DefaultExcelHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DefautRead extends AbstractRead<Map<Object, Object>> {

    @Override
    public void load(Handler<Map<Object, Object>> handler, InputStream inputStream) throws IOException {
        new DefaultExcelHandler(handler).read(inputStream);
    }
}
