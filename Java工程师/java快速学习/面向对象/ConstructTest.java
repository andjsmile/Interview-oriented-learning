import java.util.Random;

/***
 * 对象构造的各种方法
 */

public class ConstructTest{
    public static void main(String[] args){
        Employee[] staff=new Employee[3];

        staff[0]=new Employee("harry",4000);
        staff[1]=new Employee(6000);
        staff[2]=new Employee();

        for(Employee ee:staff){
            System.out.println("name="+e.getName()+",id="+e.getId()+",salary="+e.getSalary());
        }
    }
}

class Employee{
    private static int nextId;

    private String name="";   // instance field initialization 实例域的初始化
    private int    id;
    private double salary;

    // static initialization block 静态初始化块
    static{
        Random generator=new Random();
        nextId=generator.nextInt(10000);  // 0~9999
    }

    // object initialization block  对象初始化块
    {
        id=nextId;
        nextId++;
    }

    // reloaded constructor
    public Employee(String name,double salary){
        this.name=name;
        this.salary=salary;
    }

    public Employee(double salary){
        this("empolyee #"+nextId,salary);
    }

    public Employee(){

    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }
    public int getId(){
        return id;
    }
}

