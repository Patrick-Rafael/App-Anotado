package com.example.checkapp.helper;

import com.example.checkapp.model.Checks;

import java.util.List;


public interface ICheckDAO {

    public Boolean save(Checks checks);
    public Boolean update(Checks checks);
    public Boolean delete(Checks checks);
    public List<Checks> list();


}
