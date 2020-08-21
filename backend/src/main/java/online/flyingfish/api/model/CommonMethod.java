package online.flyingfish.api.model;

public class CommonMethod {
    //判断是否可以转换成整数
    public static boolean isInt(String str){
        try{
            Integer.valueOf(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
