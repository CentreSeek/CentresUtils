package com.yjjk.monitor.utility;

import com.yjjk.monitor.constant.MachineEnum;
import com.yjjk.monitor.entity.pojo.RecordBase;
import com.yjjk.monitor.entity.transaction.BackgroundSend;
import com.yjjk.monitor.mapper.PatientInfoMapper;
import com.yjjk.monitor.service.EcgService;
import com.yjjk.monitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-10-11 13:46:49
 **/
@Component
public class AsyncTask {

    @Autowired
    PatientInfoMapper patientInfoMapper;
    @Resource
    EcgService ecgService;
    @Resource
    @Lazy
    MonitorService monitorService;

    @Async
    public void exportEcgData(RecordBase recordBase, BackgroundSend backgroundSend, Integer machineId, String startTime) {
        System.out.println("===========>开始执行export");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitorService.cacheMonitorHistory(MachineEnum.ECG.getType(), recordBase.getRecordEcgId(), backgroundSend.getBaseReport(), machineId,startTime);
        //导出
//        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(recordBase.getPatientId());
//        String fileName = FileNameUtils.getEcgExportFileName(patientInfo.getName(), patientInfo.getCaseNum(), recordBase.getCreatedTime());
//        String s = null;
//        try {
//            s = FileUtils.readEcgFile("",fileName);
//        } catch (IOException e) {
//        }
//        List<EcgExportHistory> ecgExportHistories = JSON.parseArray(s, EcgExportHistory.class);
//        int i = ecgService.exportEcg(recordBase.getId(), ecgExportHistories, machineId);
//        System.out.println("心电数据导出： baseId(" + recordBase.getId() + ")   导出条数(" + i + ")");
    }

}

