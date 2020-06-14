package cn.lamamama.foi;

import cn.lamamama.foi.model.WxNumInfo;
import cn.lamamama.foi.read.DefautRead;
import cn.lamamama.foi.service.WxNumRead;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ReadTest {
    Logger log = Logger.getLogger(ReadTest.class.getName());

    @Test
    public void testAll() throws IOException {
        IRead<WxNumInfo> iRead = new WxNumRead();
        String path = ReadTest.class.getResource("/wx.xls").getPath();
        List<WxNumInfo> wxNumInfoList = iRead.all(new FileInputStream(new File(path)));
        Assert.assertEquals(5, wxNumInfoList.size());
    }

    @Test
    public void testLoad() throws Exception {
        IRead<WxNumInfo> iRead = new WxNumRead();
        String path = ReadTest.class.getResource("/wx.xls").getPath();
        iRead.load((row, wxNumInfo) -> log.info("name: " + wxNumInfo.getName() + ", no: " + wxNumInfo.getNo())
                , new FileInputStream(new File(path)));
    }

    @Test
    public void testDefautReadService() throws IOException {
        IRead<Map<Object, Object>> iRead = new DefautRead();
        String path = ReadTest.class.getResource("/wx.xls").getPath();
        List<Map<Object, Object>> list = iRead.all(new File(path));
        log.info("" + list);
        Assert.assertEquals(6, list.size());
    }
}
