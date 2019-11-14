

public class MVCPatternDemo{
    public static void main(String[] args){
        // 获取学生记录
        student model=retrieveStudentFromDataBase();

        // 创建视图
        studentView view=new studentView();

        studentController controller=new studentController(model,view);

        controller.updateView();

        // 更新模型数据
        controller.setStudentName("john");
        controller.updateView();
    }

    private static student retrieveStudentFromDataBase(){
        student stu=new student();
        stu.setName("robert");
        stu.setRollNo("10");
        return stu;
    }
}