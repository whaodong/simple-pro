package pers.hd.simplepro.core.arcgis.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import pers.hd.simplepro.core.arcgis.model.ArcGisConnectionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author WangHaoDong
 */
public class ArcgisUtil {
    /**
     * ArcGis连接参数校验
     *
     * @param info ArcGis连接信息
     * @author wanghaodong
     **/
    private static void paramCheck(ArcGisConnectionInfo info) {
        Assert.notNull(info, "ArcGis Server 连接信息不能为空!");
        Assert.notNull(info.getHostIp(), "ArcGis Server IP不能为空!");
        Assert.notNull(info.getHostPort(), "ArcGis Server Port不能为空!");
        Assert.notNull(info.getSiteUserName(), "ArcGis Server 站点用户名不能为空!");
        Assert.notNull(info.getSitePassWord(), "ArcGis Server 站点密码不能为空!");
    }

    /**
     * 获得arcgisServer Admin地址
     *
     * @param info ArcGis连接信息
     * @return java.lang.String ArcGisAdmin地址
     * @author wanghaodong
     **/
    public static String getArcgisServerAdminUrl(ArcGisConnectionInfo info) {
        paramCheck(info);
        return String.format("http://%s:%s/arcgis/admin", info.getHostIp(), info.getHostPort());
    }

    /**
     * 获得获得arcgisRestServices服务地址
     *
     * @param info ArcGis连接信息
     * @return java.lang.String ArcGis Rest服务地址
     * @author wanghaodong
     **/
    public static String getArcgisServerRestUrl(ArcGisConnectionInfo info) {
        paramCheck(info);
        return String.format("http://%s:%s/arcgis/rest/services", info.getHostIp(), info.getHostPort());
    }

    /**
     * 获得arcgisServer 站点token Url
     *
     * @param info       ArcGis连接信息
     * @param expiration 过期时间(s)
     * @return java.lang.String 站点token
     * @author wanghaodong
     **/
    public static String getArcgisServerToken(ArcGisConnectionInfo info, int expiration) {
        paramCheck(info);
        String tokenUrl = String.format("%s/generateToken", getArcgisServerAdminUrl(info));
        String credential = String.format("username=%s&password=%s&client=requestip&expiration=%d&f=json",
                info.getSiteUserName(), info.getSitePassWord(), expiration);
        return tokenUrl + "?" + credential;
    }

    /**
     * 获得arcgisServer 站点token Url
     *
     * @param info ArcGis连接信息
     * @return java.lang.String 站点token
     * @author wanghaodong
     **/
    public static String getArcgisServerToken(ArcGisConnectionInfo info) {
        paramCheck(info);
        String tokenUrl = String.format("%s/generateToken", getArcgisServerAdminUrl(info));
        String credential = String.format("username=%s&password=%s&client=requestip&expiration=&f=json",
                info.getSiteUserName(), info.getSitePassWord());
        return tokenUrl + "?" + credential;
    }

    /**
     * 获得arcgis文件夹服务状态 url
     *
     * @param arcgisServerAdminUrl arcgisServer管理员地址
     * @param token                token
     * @param folderName           文件夹名称
     * @return java.lang.String
     * @author wanghaodong arcgis状态服务状态 url
     **/
    public static String getArcgisServerStatus(String arcgisServerAdminUrl,
                                               String token,
                                               String folderName) throws Exception {
        List<HashMap<String, Object>> result = new ArrayList<>();
        String servicesUrl = String.format("%s/services/%sreport", arcgisServerAdminUrl,
                StringUtils.isEmpty(folderName) ? "" : folderName + "/");
        String credential = String.format("token=%s&f=json", token);
        return servicesUrl + "?" + credential;
    }

    /**
     * 获得arcgis服务状态 url
     *
     * @param arcgisServerAdminUrl arcgisServer管理员地址
     * @param token                token
     * @return java.lang.String
     * @author wanghaodong arcgis状态服务状态 url
     **/
    public static String getServicesStatus(String arcgisServerAdminUrl,
                                           String token) throws Exception {
        return getArcgisServerStatus(arcgisServerAdminUrl, token, "");
    }


    public static String getArcgisServices(String arcgisServerAdminUrl,
                                           String token,
                                           String folderName) {
        String servicesUrl = String.format("%s/services/%s", arcgisServerAdminUrl, folderName);
        String credential = String.format("token=%s&f=json", token);
        return servicesUrl + "?" + credential;
    }
}