import java.util.concurrent.TimeUnit;

interface DoHomeWork {
    void doHomeWork(String question, String answer);
}

public class Student implements DoHomeWork {

    public int x = 1;
    @Override
    public void doHomeWork(String question, String answer) {
        System.out.println("作业本");
        if (answer != null) {
            System.out.println("作业：" + question + " 答案：" + answer);
        } else {
            System.out.println("作业：" + question + " 答案：" + "(空白)");
        }
    }

    public void ask(final String homework, final RoomMate roomMate) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                roomMate.getAnswer(homework, Student.this);
            }
        }).start();

        goHome();
    }

    public void goHome() {
        System.out.println("我回家了……好室友，帮我写下作业。");
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.x = 2;
        String homework = "当x趋向于0，sin(x)/x =?";
        student.ask(homework, new RoomMate());

    }
}

class RoomMate {

    public void getAnswer(String homework, DoHomeWork someone) {
        if ("1+1=?".equals(homework)) {
            someone.doHomeWork(homework, "2");
        } else if ("当x趋向于0，sin(x)/x =?".equals(homework)) {

            System.out.print("思考：");
            for (int i = 1; i <= 3; i++) {
                System.out.print(i + "秒 ");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(((Student)someone).x);
            someone.doHomeWork(homework, "1");
        } else {
            someone.doHomeWork(homework, "(空白)");
        }
    }

}
