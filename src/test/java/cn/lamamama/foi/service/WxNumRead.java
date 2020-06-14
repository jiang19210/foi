package cn.lamamama.foi.service;

import cn.lamamama.foi.handler.WxNumExcelHandler;
import cn.lamamama.foi.model.WxNumInfo;
import cn.lamamama.foi.read.AbstractRead;

import java.io.IOException;
import java.io.InputStream;

public class WxNumRead extends AbstractRead<WxNumInfo> {

    @Override
    public void load(Handler<WxNumInfo> handler, InputStream inputStream) throws IOException {
        new WxNumExcelHandler(handler).read(inputStream);
    }
}
