package Test1;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void findTxtFiles(File dir, List<File> allTxtFiles) { //нахождение всех .txt файлов во всех используя рекурсию
        File[] allFiles = dir.listFiles();
        for(int i = 0; i < allFiles.length; i++)
            if(allFiles[i].isFile() && allFiles[i].getName().endsWith(".txt")) allTxtFiles.add(allFiles[i]);
            else if(allFiles[i].isDirectory()) Task1.findTxtFiles(allFiles[i],allTxtFiles);
    }
    public static void main(String[] args) throws IOException {
        File rootDir = new File("/Users/anastas/Desktop/RootDirectory");
        List<File> allTxtFiles = new ArrayList<File>();
        Task1.findTxtFiles(rootDir,allTxtFiles);
        //сортировка файлов по имени
        Collections.sort(allTxtFiles, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        String o1Comp = o1.getName().substring(0,o1.getName().lastIndexOf(".txt"));
                        String o2Comp = o2.getName().substring(0,o2.getName().lastIndexOf(".txt"));
                        return o1Comp.compareTo(o2Comp);
                    }
                }
        );
        allTxtFiles.stream().forEach((f)->System.out.println(f.getName()));
        File resultFile = new File("/Users/anastas/Desktop/resultTxt.txt");
        if(resultFile.exists()) resultFile.delete();
        resultFile.createNewFile();
        BufferedWriter resultFileWriter = new BufferedWriter(new FileWriter(resultFile,true));
        //склеивание содержимого всех отсортированных файлов в отдельный resultTxt.txt файл
        for(int i = 0; i < allTxtFiles.size(); i++){
            BufferedReader txtFileReader = new BufferedReader(new FileReader(allTxtFiles.get(i)));
            String str = null;
            while ((str = txtFileReader.readLine()) != null) {
                resultFileWriter.write(str);
                resultFileWriter.newLine();
            }

            txtFileReader.close();
        }
        resultFileWriter.close();
    }
}
