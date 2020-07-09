import java.io.*;
import java.util.Scanner;

public class T1 {
    public static void main(String[] args) {
        //文件名
        String filename = "./src/student.txt";
        //文件写入流
        PrintWriter printWriter = null;
        //声明字符流
        BufferedReader bufferedReader = null;
        String line = null;

        Scanner scanner = new Scanner(System.in);
        try {
            printWriter = new PrintWriter(filename);
            //输入学生信息并写入文件
            while (true) {
                System.out.println("请输入学号，姓名，课程代码，平均成绩, 结束请输入over");

                String studentNo = scanner.next();
                if ("over".equals(studentNo)) {
                    break;
                }
                String name = scanner.next();
                String courseNo = scanner.next();
                int score = scanner.nextInt();

                printWriter.println(studentNo + "\t" + name + "\t" + courseNo + "\t" + score);
            }
            //关闭写入流保存文件
            printWriter.close();

            //创建字符流读取文件逐行输出
            bufferedReader = new BufferedReader(new FileReader(filename));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            //关闭字符流
            bufferedReader.close();

            while (true) {
                System.out.println("请输入要查询的学生学号， 结束请输入over");
                String studentNo = scanner.next();
                if ("over".equals(studentNo)) {
                    break;
                }

                System.out.println(String.format("输出学号为%s的学生查询结果", studentNo));
                //是否查询成功
                boolean flag = false;
                //创建字符流逐行对比学号属性
                bufferedReader = new BufferedReader(new FileReader(filename));
                while ((line = bufferedReader.readLine()) != null) {
                    //按\t分割字符串取第一个元素为学号
                    String[] strs = line.split("\t");
                    if (studentNo.equals(strs[0])) {
                        flag = true;
                        System.out.println(line);
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("查询结果为空");
                }
                //关闭字符流
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {
            //异常处理，文件不存在时打印异常堆栈
            e.printStackTrace();
        } catch (IOException e) {
            //异常处理，IO异常时打印异常堆栈
            e.printStackTrace();
        } finally {
            //异常处理，无论是否发生异常都会执行以下操作，关闭写入流与字符流
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}