package pers.hd.simplepro.core.arcgis.model;

import java.util.Objects;

/**
 * @author WangHaoDong
 */
public class ArcGisConnectionInfo {
    /**
     * Arcgis服务器IP
     */
    private String hostIp;

    /**
     * Arcgis 端口
     */
    private String hostPort;

    /**
     * Arcgis 站点用户名
     */
    private String siteUserName;

    /**
     * Arcgis 站点密码
     */
    private String sitePassWord;

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getSiteUserName() {
        return siteUserName;
    }

    public void setSiteUserName(String siteUserName) {
        this.siteUserName = siteUserName;
    }

    public String getSitePassWord() {
        return sitePassWord;
    }

    public void setSitePassWord(String sitePassWord) {
        this.sitePassWord = sitePassWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArcGisConnectionInfo)) {
            return false;
        }
        ArcGisConnectionInfo that = (ArcGisConnectionInfo) o;
        return Objects.equals(hostIp, that.hostIp) && Objects.equals(hostPort, that.hostPort) && Objects.equals(siteUserName, that.siteUserName) && Objects.equals(sitePassWord, that.sitePassWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostIp, hostPort, siteUserName, sitePassWord);
    }
}

