package com.solvd.essay.service;

import com.solvd.essay.domain.GasConsumptionEssay;
import com.solvd.essay.domain.LabTestReport;
import com.solvd.essay.persistence.impl.AbstracDao;
import com.solvd.essay.persistence.impl.LabTestReportRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GasConsumptionEssayService {
    private final AbstracDao<GasConsumptionEssay> gasConsumptionEssayImpl;

    public GasConsumptionEssayService(AbstracDao<GasConsumptionEssay> gasConsumptionEssayAbstracDao){
        this.gasConsumptionEssayImpl =gasConsumptionEssayAbstracDao;
    }

    public void create(GasConsumptionEssay gasConsumptionEssay){
        try {
            gasConsumptionEssayImpl.create(gasConsumptionEssay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<GasConsumptionEssay> findAll() throws SQLException {
        List<GasConsumptionEssay> listOfGasEssays=gasConsumptionEssayImpl.getAll();
        AbstracDao<LabTestReport> labTestReportImpl=new LabTestReportRepositoryImpl();
        LabTestReportService newLabTestReportService= new LabTestReportService(labTestReportImpl);
        for (GasConsumptionEssay gasConEssay:listOfGasEssays) {
            gasConEssay.setLabTestReport(newLabTestReportService.findOne(gasConEssay.getLabTestReportId()));
        }
        return listOfGasEssays;

    }

    public GasConsumptionEssay findOne(Long id) throws SQLException {
        GasConsumptionEssay gasConEssay= gasConsumptionEssayImpl.findById(id);
        if (gasConEssay==null){
            return new GasConsumptionEssay();
        }
        AbstracDao<LabTestReport> labTestReportImpl=new LabTestReportRepositoryImpl();
        LabTestReportService newLabTestReportService= new LabTestReportService(labTestReportImpl);
        gasConEssay.setLabTestReport(newLabTestReportService.findOne(gasConEssay.getLabTestReportId()));
        return gasConEssay;
    }

    public void deleteOne(Long id) throws SQLException {
        gasConsumptionEssayImpl.deleteById(id);
    }

    public void deleteEntity(GasConsumptionEssay gasConsumptionEssay){
        gasConsumptionEssayImpl.delete(gasConsumptionEssay);
    }

    public void updateEntity(GasConsumptionEssay gasConsumptionEssay){
        try {
            gasConsumptionEssayImpl.update(gasConsumptionEssay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
