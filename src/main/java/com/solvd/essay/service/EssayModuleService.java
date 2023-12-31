package com.solvd.essay.service;

import com.solvd.essay.domain.EquipmentForTestModel;
import com.solvd.essay.domain.EssayModule;
import com.solvd.essay.persistence.impl.AbstracDao;

import java.sql.SQLException;
import java.util.List;

public class EssayModuleService {
    private final AbstracDao<EssayModule> essayModuleImpl;

    public EssayModuleService(AbstracDao<EssayModule> essayModuleAbstracDao){
        this.essayModuleImpl =essayModuleAbstracDao;
    }

    public void create(EssayModule essayModule){
        try {
            essayModuleImpl.create(essayModule);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<EssayModule> findAll() throws SQLException {
            return essayModuleImpl.getAll();
    }

    public EssayModule findOne(Long id) throws SQLException {
        EssayModule result= essayModuleImpl.findById(id);
        if (result==null){
            return new EssayModule();
        }
        return result;
    }

    public void deleteOne(Long id) throws SQLException {
        essayModuleImpl.deleteById(id);
    }

    public void deleteEntity(EssayModule essayModule){
        essayModuleImpl.delete(essayModule);
        /*
        System.out.println("Object with id= "+essayModule.xml.getId()+"and "
                +essayModule.xml.getModuleDescription()+" deleted successfully");
    */
    }

    public void updateEntity(EssayModule essayModule){
        try {
            essayModuleImpl.update(essayModule);
            /*
            System.out.println("Object with id= "+essayModule.xml.getId()+"and "
                    +essayModule.xml.getModuleDescription()+" was updated successfully");
             */
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
