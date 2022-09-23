package main.java.application;

import main.java.application.model.dao.DaoFactory;
import main.java.application.model.dao.DepartmentDao;
import main.java.application.model.entities.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentRun {
    public static void main(String[] args) throws SQLException {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();


        System.out.println("FindById");
        Department departmentId = departmentDao.findById(5);
        System.out.println(departmentId);

        System.out.println("-------------------");
        System.out.println("List FindAll Department");
        List<Department> listFindAll = departmentDao.findAll();
        for (Department inList: listFindAll) {
            System.out.println(inList);
        }

        System.out.println("-------------------");
        System.out.println("Insert new Department");
        Department dp = new Department(null, "Operations");
        departmentDao.insert(dp);
        System.out.println("Inserted! New id is: "+ dp.getId());

        System.out.println("________________");
        System.out.println("Update Department");
        departmentId = departmentDao.findById(10);
        departmentId.setName("People");
        departmentDao.update(departmentId);

        System.out.println("________________");
        System.out.println("Delete Department");
        departmentDao.deleteById(9);

    }
}


