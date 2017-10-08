package pl.infoshareacademy;

public class Configuration {
    private String filePath;
    private String linkForCPC;
    private String linkForSCC;
    private String linkForAL;
    private int minPhraseLength;

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
}
