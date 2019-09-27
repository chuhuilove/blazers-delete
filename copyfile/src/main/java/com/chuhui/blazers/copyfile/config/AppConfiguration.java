package com.chuhui.blazers.copyfile.config;

/**
 * AppConfiguration
 *
 * @author: cyzi
 * @Date: 2019/9/27 0027
 * @Description:TODO
 */
public class AppConfiguration {

    private TargetConfiguration main;

    public TargetConfiguration getMain() {
        return main;
    }

    public void setMain(TargetConfiguration main) {
        this.main = main;
    }

    public static class TargetConfiguration {

        private String sourcePath;
        private String targetPath;
        private String version = "5.1.10.cyzi";

        private Boolean deleteSource;
        private Boolean deleteSourcePath;
        private String deletSourcePathFlag=".m2";

        public String getDeletSourcePathFlag() {
            return deletSourcePathFlag;
        }

        public void setDeletSourcePathFlag(String deletSourcePathFlag) {
            this.deletSourcePathFlag = deletSourcePathFlag;
        }

        public Boolean getDeleteSource() {
            return deleteSource;
        }

        public void setDeleteSource(Boolean deleteSource) {
            this.deleteSource = deleteSource;
        }

        public Boolean getDeleteSourcePath() {
            return deleteSourcePath;
        }

        public void setDeleteSourcePath(Boolean deleteSourcePath) {
            this.deleteSourcePath = deleteSourcePath;
        }

        public String getSourcePath() {
            return sourcePath;
        }

        public void setSourcePath(String sourcePath) {
            this.sourcePath = sourcePath;
        }

        public String getTargetPath() {
            return targetPath;
        }

        public void setTargetPath(String targetPath) {
            this.targetPath = targetPath;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

}
