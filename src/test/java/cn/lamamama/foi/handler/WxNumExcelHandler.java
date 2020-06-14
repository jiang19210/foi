package cn.lamamama.foi.handler;

import cn.lamamama.foi.model.WxNumInfo;
import cn.lamamama.foi.IRead;

import java.util.Map;

public class WxNumExcelHandler extends AbstractExcel2003Handler {
    IRead.Handler<WxNumInfo> handler;

    public WxNumExcelHandler(IRead.Handler<WxNumInfo> handler) {
        this.handler = handler;
    }

    @Override
    public void optRows(int row, Map<Object, Object> rowValueMap) {
        if (row > 0) {
            WxNumInfo wxNumInfo = new WxNumInfo();
            wxNumInfo.setName(rowValueMap.get("name").toString());
            wxNumInfo.setNo(rowValueMap.get("no").toString());
            handler.handle(row, wxNumInfo);
        }
    }
}
