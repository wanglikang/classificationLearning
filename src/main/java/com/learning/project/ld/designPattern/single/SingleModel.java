package com.learning.project.ld.designPattern.single;

/**
 * 单例模式
 * @author lvdong
 * date 2019-08-23
 */
public class SingleModel {
    public static void main(String[] args){
        new TestThread().start();
        new TestThread().start();
        new TestThread().start();
    }

}
class TestThread extends Thread{
    @Override
    public void run() {
        //LazySingleModel lazySingleModel=new LazySingleModel();
        LazySingleModel lazySingleModel= LazySingleModel.getInstance();
        System.out.println(lazySingleModel.getName());

    }
}

/**
 * 懒汉式单例
 */
class LazySingleModel{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static LazySingleModel lazySingleModel = null;
    public static synchronized LazySingleModel getInstance(){
        if(null==lazySingleModel){
            lazySingleModel=new LazySingleModel();
        }
        return lazySingleModel;
    }

    /**
     * 私有够造方法 防止外部实例化
     */
    private LazySingleModel(){
        this.name="lazyName";
        System.out.println("LazySingleModel is init");
    };

}

/**
 * 饿汉式单例
 */
class HungrySingleModel{
    /*
    private final static HungrySingleModel hungrySingleModel;

    static{
        hungrySingleModel=new HungrySingleModel();
    }
    */
    private final static HungrySingleModel hungrySingleModel  = new HungrySingleModel();
    private HungrySingleModel(){}
    public static HungrySingleModel getInstance(){
        return hungrySingleModel;
    }
}

/**
 * 静态内部类
 */
class StaticClassSingleModle{
    private static class SingleClassInstance{
        public static final StaticClassSingleModle staticClassSingleModle = new StaticClassSingleModle();
        private SingleClassInstance(){
        }
        public StaticClassSingleModle getInstance(){
            return SingleClassInstance.staticClassSingleModle;
        }
    }
}

/**
 * 双重锁判断
 */
class DoubleSyncSingleModel{
    private static volatile DoubleSyncSingleModel doubleSyncSingleModel;
    private DoubleSyncSingleModel(){ }
    public static DoubleSyncSingleModel getInstance(){
        if(null==doubleSyncSingleModel){
            synchronized (doubleSyncSingleModel){
                if(null==doubleSyncSingleModel){
                    doubleSyncSingleModel=new DoubleSyncSingleModel();
                }
            }
        }
        return doubleSyncSingleModel;
    }
}
