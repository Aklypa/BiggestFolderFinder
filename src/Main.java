import java.io.File;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) {

//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//
//        thread.start();
//        thread2.start();

        String folderPath = "C:\\Users\\aklyp\\OneDrive\\Рабочий стол\\Новая папка";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        //System.out.println(file.length());

        //System.out.println(getFolderSize(file));

        long duration = (System.currentTimeMillis() - start) / 1000;
        System.out.println(duration + " seconds");

    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}