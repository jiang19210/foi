package cn.lamamama.foi.handler;

import cn.lamamama.foi.IRead;
import com.google.common.collect.Maps;

import java.util.Map;

public class DefaultExcelHandler extends AbstractExcel2003Handler {

    IRead.Handler<Map<Object, Object>> handler;

    public DefaultExcelHandler(IRead.Handler<Map<Object, Object>> handler) {
        this.handler = handler;
    }

    @Override
    public void optRows(int row, Map<Object, Object> rowValueMap) {
        handler.handle(row, Maps.newHashMap(rowValueMap));
    }
}
