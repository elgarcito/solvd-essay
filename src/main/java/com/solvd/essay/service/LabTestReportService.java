package com.solvd.essay.service;

import com.solvd.essay.domain.*;
import com.solvd.essay.persistence.impl.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LabTestReportService {
    private final AbstracDao<LabTestReport> labTestReportImpl;

    public LabTestReportService(AbstracDao<LabTestReport> labTestReportAbstracDao){
        this.labTestReportImpl =labTestReportAbstracDao;
    }

    public void create(LabTestReport labTestReport){
        try {
            labTestReportImpl.create(labTestReport);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LabTestReport> findAll() throws SQLException {
        List<LabTestReport> labTestReportList=labTestReportImpl.getAll();

        AbstracDao<EquipmentForTestModel> equipmentForTestModelImpl= new EquipmentForTestModelRepositoryImpl();
        EquipmentForTestModelService newEquipmentForTestModelService= new EquipmentForTestModelService(equipmentForTestModelImpl);

        AbstracDao<BatchInfo> newBatchInfoImplementation= new BatchInfoRepositoryImpl();
        BatchInfoService newBatchInfoService= new BatchInfoService(newBatchInfoImplementation);

        AbstracDao<Employee> newEmployeeImplementation= new EmployeeRepositoryImpl();
        EmployeeService newEmployeeService= new EmployeeService(newEmployeeImplementation);

        AbstracDao<EssayModule> newEssayModuleImplementation = new EssayModuleRepositoryImpl();
        EssayModuleService newEssayModuleService= new EssayModuleService(newEssayModuleImplementation);

        for (LabTestReport labTestReport: labTestReportList) {
            labTestReport.setEquipmentForTestModel(newEquipmentForTestModelService.findOne(labTestReport.getEquipmentForTestModelId()));
            labTestReport.setBatchInfo(newBatchInfoService.findOne(labTestReport.getBatchInfoId()));
            labTestReport.setEmployee(newEmployeeService.findOne(labTestReport.getEmployeeId()));
            labTestReport.setEssayModule(newEssayModuleService.findOne(labTestReport.getEssayModuleId()));
        }
            return labTestReportList;
    }

    public LabTestReport findOne(Long id) throws SQLException {

        LabTestReport labTestReport=labTestReportImpl.findById(id);

        if (labTestReport==null){
            return new LabTestReport();
        }

        AbstracDao<EquipmentForTestModel> equipmentForTestModelImpl= new EquipmentForTestModelRepositoryImpl();
        EquipmentForTestModelService newEquipmentForTestModelService= new EquipmentForTestModelService(equipmentForTestModelImpl);

        AbstracDao<BatchInfo> newBatchInfoImplementation= new BatchInfoRepositoryImpl();
        BatchInfoService newBatchInfoService= new BatchInfoService(newBatchInfoImplementation);

        AbstracDao<Employee> newEmployeeImplementation= new EmployeeRepositoryImpl();
        EmployeeService newEmployeeService= new EmployeeService(newEmployeeImplementation);

        AbstracDao<EssayModule> newEssayModuleImplementation = new EssayModuleRepositoryImpl();
        EssayModuleService newEssayModuleService= new EssayModuleService(newEssayModuleImplementation);

        labTestReport.setEquipmentForTestModel(newEquipmentForTestModelService.findOne(labTestReport.getEquipmentForTestModelId()));
        labTestReport.setBatchInfo(newBatchInfoService.findOne(labTestReport.getBatchInfoId()));
        labTestReport.setEmployee(newEmployeeService.findOne(labTestReport.getEmployeeId()));
        labTestReport.setEssayModule(newEssayModuleService.findOne(labTestReport.getEssayModuleId()));

        return labTestReport;
    }

    public void deleteOne(Long id) throws SQLException {
        labTestReportImpl.deleteById(id);
    }

    public void deleteEntity(LabTestReport labTestReport){
        labTestReportImpl.delete(labTestReport);
    }

    public void updateEntity(LabTestReport labTestReport){
        try {
            labTestReportImpl.update(labTestReport);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
