package com.example.niit.greenrouse.util;

import cn.bmob.v3.BmobObject;

/**
 * Created by wangyejun on 2017/5/12.
 */

public class User extends BmobObject{
    private String name;
    private String password;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
