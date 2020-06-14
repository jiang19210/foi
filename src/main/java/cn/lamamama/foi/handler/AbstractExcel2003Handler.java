package cn.lamamama.foi.handler;

import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExcel2003Handler implements HSSFListener {

    private SSTRecord sstRecord;

    private int row = 0;

    private final Map<Object, Object> rowValueMap = new HashMap<>();
    private final Map<Object, Object> columnValueMap = new HashMap<>();

    public void processRecord(Record record) {
        switch (record.getSid()) {
            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;
            case NumberRecord.sid:
                NumberRecord numrec = (NumberRecord) record;
                if (row == 0) {
                    columnValueMap.put(numrec.getColumn(), numrec.getValue());
                } else {
                    rowValueMap.put(columnValueMap.get(numrec.getColumn()).toString(), numrec.getValue());
                }
                break;
            case LabelSSTRecord.sid:
                LabelSSTRecord lrec = (LabelSSTRecord) record;
                if (row == 0) {
                    columnValueMap.put(lrec.getColumn(), sstRecord.getString(lrec.getSSTIndex()));
                } else {
                    rowValueMap.put(columnValueMap.get(lrec.getColumn()).toString(), sstRecord.getString(lrec.getSSTIndex()));
                }
                break;
        }
        if (record instanceof LastCellOfRowDummyRecord) {
            if (row == 0) {
                optRows(row, columnValueMap);
            } else {
                optRows(row, rowValueMap);
                rowValueMap.clear();
            }
            row ++;
        }
    }
    /**
     * 处理单行数据的回调方法。
     * @param rowValueMap 当前行的值
     */
    public abstract void optRows(int row, Map<Object, Object> rowValueMap);

    public void read(InputStream inputStream) throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem(inputStream)) {
            MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
            FormatTrackingHSSFListener formatListener = new FormatTrackingHSSFListener(listener);
            HSSFEventFactory factory = new HSSFEventFactory();
            HSSFRequest request = new HSSFRequest();
            request.addListenerForAllRecords(formatListener);
            factory.processWorkbookEvents(request, fs);
        }
    }

    public void read(File filePath) throws IOException {
        read(new FileInputStream(filePath));
    }
}