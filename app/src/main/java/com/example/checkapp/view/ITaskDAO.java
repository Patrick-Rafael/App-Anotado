package com.example.checkapp.view;

import com.example.checkapp.model.Checks;

import java.util.List;


public interface ITaskDAO {

    public Boolean save(Checks checks);
    public Boolean update(Checks checks);
    public Boolean delete(Checks checks);
    public List<Checks> list();


}
