package cn.xiami.module;

public class ModuleFactory {

    public static Category getCategory(){
        return new Category();
    }

    public static Cinfo getCinfo(){
        return new Cinfo();
    }

    public static Music getMusic(){
        return new Music();
    }

    public static User getUser(){
        return new User();
    }

}
