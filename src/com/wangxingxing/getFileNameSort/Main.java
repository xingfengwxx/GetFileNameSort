package com.wangxingxing.getFileNameSort;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String PATH_FOLDER_SOURCE = "E:\\UI\\表情包\\表情包2";
    public static final String PATH_DRAWABLE_XXXHDPI = "E:\\UI\\表情包\\drawable-xxxhdpi";

    public static void main(String[] args) {
//       getFileNameSort();

        replaceNameToAndroidDrawable();
    }

    public static void getFileNameSort() {
        System.out.println("获取文件名并排序===");
        File file = new File(PATH_FOLDER_SOURCE);
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("png")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String[] fileNames = file.list(filenameFilter);
        System.out.println("文件名数组：" + Arrays.toString(fileNames));

        List<FileNameBean> fileNameBeans = new ArrayList<>();
        for (String name : fileNames) {
            name = name.replace("[", "|")
                    .replace("]", "|")
                    .replace(".", "");
            System.out.println("replace name=" + name);
            String[] strs = name.split("\\|");
            System.out.println("strs[0]=" + strs[0] + ", strs[1]=" + strs[1] + ", strs[2]=" + strs[2]);
            fileNameBeans.add(new FileNameBean(Integer.parseInt(strs[0]), strs[1]));
        }

        //排序
        Collections.sort(fileNameBeans);

        String[] ret = new String[fileNameBeans.size()];
        for (int i = 0; i < fileNameBeans.size(); i++) {
            System.out.println("ret order=" + fileNameBeans.get(i).getOrder() + ", ret name=" + fileNameBeans.get(i).getName());
            ret[i] = "[" + fileNameBeans.get(i).getName() + "]";
        }

        String retStr = Arrays.toString(ret).replace(", ", ",");
        System.out.println(retStr);
    }

    public static void replaceNameToAndroidDrawable() {
        System.out.println("批量替换文件名，如：1[沉思].png 转成 emoji_1.png===");
        File file = new File(PATH_DRAWABLE_XXXHDPI);

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("png")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        File[] files = file.listFiles(filenameFilter);
        for (File f : files) {
            //替换名字，匹配[]中的内容
            String sourceName = f.getName();
            String replaceName = "";
            String destName = "";
            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(sourceName);
            while (matcher.find()) {
//                System.out.println(matcher.group(1));
                replaceName = sourceName.replace(matcher.group(0), "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace("@3x", "");
                System.out.println("replaceName: " + replaceName);

                if (replaceName.equals("emoji_")) {
                    System.out.println("ERROR: " + destName);
                }
            }

            destName = PATH_DRAWABLE_XXXHDPI + File.separator + "emoji_" + replaceName;

            System.out.println("destName=" + destName);

            f.renameTo(new File(destName));
        }
    }
}
