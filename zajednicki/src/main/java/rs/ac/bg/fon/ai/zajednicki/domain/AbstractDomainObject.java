/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public abstract class AbstractDomainObject implements Serializable {
    
    public abstract String getTableName();
    public abstract String alijas();
    public abstract String join();
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
    public abstract String getColumnsForInsert();
    public abstract String getPrimaryKey();
    public abstract String getValuesForInsert();
    public abstract String getValuesForUpdate();
    public abstract String condition();
    
    
}
