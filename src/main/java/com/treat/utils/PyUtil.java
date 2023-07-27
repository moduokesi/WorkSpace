package com.treat.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PyUtil {
    public static Object SegFiles(String fileName, String fileAccount) {
        String pythonScript = "";
        String configPath = "D:\\PycharmProject\\pythonProject_vnet\\baseline_model\\vnet\\export_model\\deploy.yaml";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName;
        String saveDir = "";

        // 构建命令行参数
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScript, "--config", configPath, "--image_path", imageFilePath, "--save_dir", saveDir);
        processBuilder.redirectErrorStream(true); //重定向错误流到输入流

        try {
            Process process = processBuilder.start();

            // 获取输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void NiiToStl(String fileName, String fileAccount) {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\nii2stl.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName;
        String saveDir = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\";

        // 构建命令行参数
        String[] command = new String[]{"python", pythonScript, "--image_path", imageFilePath, "--save_dir", saveDir};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void separateOraganStl(String fileName, String fileAccount, String url) {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\separate_organs_stl.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName;
        String saveDir = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\" + url;

        // 构建命令行参数
        String[] command = new String[]{"python", pythonScript, "--image_path", imageFilePath, "--save_dir", saveDir};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void separateOraganNii(String fileName, String fileAccount, String url) {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\separate_organs_nii.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName;
        String saveDir = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\" + url;

        // 构建命令行参数
        String[] command = new String[]{"python", pythonScript, "--image_path", imageFilePath, "--save_dir", saveDir};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Double> OrganSurfaceMeasure(String fileName, String fileAccount) {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\multi_organs_surface_measurement.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName + ".nii.gz";

        // 构建命令行参数
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScript, "--image_path", imageFilePath);
        processBuilder.redirectErrorStream(true); //重定向错误流到输入流

        try {
            Process process = processBuilder.start();

            // 获取输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            ArrayList<Double> array = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                array.add(Double.valueOf(line));
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

            return array;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Double> OrganVolumnMeasure(String fileName, String fileAccount) {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\multi_organs_volume_measurement.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName + ".nii.gz";

        // 构建命令行参数
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScript, "--image_path", imageFilePath);
        processBuilder.redirectErrorStream(true); //重定向错误流到输入流

        try {
            Process process = processBuilder.start();

            // 获取输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            ArrayList<Double> array = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                array.add(Double.valueOf(line));
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

            return array;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Double> OrganDiameterMeasure(String fileName, String fileAccount) {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\multi_organs_diameter_measurement.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount + "\\outfile\\dataset\\" + fileName + ".nii.gz";

        // 构建命令行参数
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScript, "--image_path", imageFilePath);
        processBuilder.redirectErrorStream(true); //重定向错误流到输入流

        try {
            Process process = processBuilder.start();

            // 获取输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            ArrayList<Double> array = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                array.add(Double.valueOf(line));
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

            return array;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}