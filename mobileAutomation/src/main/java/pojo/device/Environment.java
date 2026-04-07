package pojo.device;

public class Environment {
    private String deviceName;
    private String platformName;
    private String platformVersion;
    private String udid;
    private String appPackage;
    private String appActivity;

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getPlatformName() { return platformName; }
    public void setPlatformName(String platformName) { this.platformName = platformName; }

    public String getPlatformVersion() { return platformVersion; }
    public void setPlatformVersion(String platformVersion) { this.platformVersion = platformVersion; }

    public String getUdid() { return udid; }
    public void setUdid(String udid) { this.udid = udid; }

    public String getAppPackage() { return appPackage; }
    public void setAppPackage(String appPackage) { this.appPackage = appPackage; }

    public String getAppActivity() { return appActivity; }
    public void setAppActivity(String appActivity) { this.appActivity = appActivity; }
}
