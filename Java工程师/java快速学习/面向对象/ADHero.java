/**
 * 方法重载
 */

//package 面向对象
public class ADHero extends Hero{
    public void attack(){

    }
    // 相同类型的可变参数
    public void attack(Hero... heros){
        for(int i=0;i<heros.length;++i){

        }
    }
}