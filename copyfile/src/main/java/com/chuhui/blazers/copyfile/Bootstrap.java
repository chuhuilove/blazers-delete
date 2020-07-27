package com.chuhui.blazers.copyfile;

import com.chuhui.blazers.copyfile.config.AppConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Bootstrap
 *
 * windows下gradle编译了spring-source,并且进行install之后,产生的jar包在%USER_HOME%/.m2/org/framework/...下面
 *
 * 但是个人的本地maven仓库在其他路径下,为了统一路径,遂编写程序将%USER_HOME%/.m2/org/framework/...下新产生的jar包移动到本地maven仓库下
 *
 * 其实,这个功能的实现方式,有很多种,可以用很多种语言编写.
 * 一开始想写批处理文件,但是对批处理文件的语法不熟悉.
 *
 * 需要一些配置文件,在本模块下的resource/config.yml
 *
 * 或者,直接修改在spring-framework中的build.gradle文件可以做到直接安装jar包到本地仓库..但是本人对gradle是在不熟悉,
 * 而且目前的用途仅限于编译spring-framework的编译.花费大量时间去学习gradle,个人认为得不偿失.
 *
 * @author: cyzi
 * @Date: 2019/9/27 0027
 */
public class Bootstrap {


    public static void main(String[] args) {


        AppConfiguration config = getConfiguration();

        String sourcePath = config.getMain().getSourcePath();

        String targetPath = config.getMain().getTargetPath();

        File sourceRootFile = checkIsDirectory(sourcePath);


        File[] sourceChildFiles = sourceRootFile.listFiles();

        File targetRootFile = checkIsDirectory(targetPath);


        File[] targetChildFiles = targetRootFile.listFiles();


        // sourceChildFiles :spring-aop级别的
        //todo 这时间复杂度...
        for (File sourceChildFile : sourceChildFiles) {

            // 找到 目标文件夹下spring-aop级别的目录
            File targetFile = null;

            for (File targetChildFile : targetChildFiles) {
                if (sourceChildFile.getName().equals(targetChildFile.getName())) {
                    targetFile = targetChildFile;
                    break;
                }
            }


            if (targetFile != null) {
                File[] deleteDirectorys = targetFile.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.equals(config.getMain().getVersion());
                    }
                });

                if (deleteDirectorys != null && deleteDirectorys.length > 0) {
                    // 找到目标文件夹下和版本一致的目录
                    File deleteDirectory = deleteDirectorys[0];
                    copyFile(sourceChildFile, deleteDirectory);
                } else {
                    // 如果版本一致的目录不存在
                    File targetDirectory = new File(targetFile.getAbsolutePath() + File.separatorChar + config.getMain().getVersion());
                    if (!targetDirectory.exists()) {
                        targetDirectory.mkdir();
                    }
                    copyFile(sourceChildFile, targetDirectory);
                }
            } else {
                // spring-aop这种级别的文件夹不存在
                String createPath = config.getMain().getTargetPath() + File.separatorChar + sourceChildFile.getName() + File.separatorChar + config.getMain().getVersion();
                File file = new File(createPath);
                if (!file.exists()) {
                    file.mkdirs();
                    copyFile(sourceChildFile, file);
                }
            }

            if(config.getMain().getDeleteSource()){
                deleteDirectory(sourceChildFile, true);
            }
        }

        // 判断是否需要删除类似于.m2的文件夹
        if(config.getMain().getDeleteSourcePath()){

            File parentFile = sourceRootFile.getParentFile();
            while (!parentFile.getName().equals(config.getMain().getDeletSourcePathFlag())){
                parentFile=parentFile.getParentFile();
            }
            deleteDirectory(parentFile,true);
        }

    }


    private static File checkIsDirectory(String path) {
        File directory = new File(path);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new RuntimeException(path + " is not exists or not directory");
        }
        return directory;
    }

    private static AppConfiguration getConfiguration() {
        AppConfiguration config = null;

        Yaml yaml = new Yaml();
        try (InputStream in = ClassLoader.getSystemResourceAsStream("config.yml")) {
            config = yaml.loadAs(in, AppConfiguration.class);
        } catch (Exception ex) {
            String message = ex.getMessage();
            throw new RuntimeException(message);
        }

        return config;
    }

    private static void deleteDirectory(File file) {
        deleteDirectory(file, false);
    }

    private static void deleteDirectory(File file, boolean isDeleteSelf) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File delete : files) {
                deleteDirectory(delete, isDeleteSelf);
            }
            if (isDeleteSelf) {
                file.delete();
            }
        } else {
            file.delete();
        }

    }

    static void copyFile(File sourceDirectory, File deleteDirectory) {

        // 递归删除该目录下的所有文件
        deleteDirectory(deleteDirectory);

        String targetAbsolutePath = deleteDirectory.getAbsolutePath();

        File[] files = sourceDirectory.listFiles();

        if (files != null) {

            for (File file : files) {
                if (file.isDirectory()) {

                    File[] files1 = file.listFiles();

                    if (files1 != null) {

                        for (File file1 : files1) {

                            String[] split = file1.getAbsolutePath().split(File.separatorChar+"");
                            String fileName = split[split.length - 1];
                            file1.renameTo(new File(targetAbsolutePath + File.separatorChar+ fileName));

                        }
                    }
                }
            }
        }

    }


}
