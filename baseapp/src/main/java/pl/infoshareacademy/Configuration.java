package pl.infoshareacademy;

public class Configuration {
    private String filePath;
    private String linkForCPC;
    private String linkForSCC;
    private String linkForAL;
    private int minPhraseLength;
    private String facebookAppId;
    private String testFacebookAppId;
    private String facebookAppSecret;
    private String testFacebookAppSecret;
    private boolean useTestFB;

    public boolean isUseTestFB() {
        return useTestFB;
    }

    public String getTestFacebookAppId() {
        return testFacebookAppId;
    }

    public String getFacebookAppSecret() {
        return facebookAppSecret;
    }

    public String getTestFacebookAppSecret() {
        return testFacebookAppSecret;
    }

    public Configuration() { }

    public String getLinkForSCC() {
        return linkForSCC;
    }

    public String getLinkForCPC() {
        return linkForCPC;
    }

    public int getMinPhraseLength() {
        return minPhraseLength;
    }

    public String getLinkForAL() {
        return linkForAL;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFacebookAppId() {return facebookAppId;}
}
