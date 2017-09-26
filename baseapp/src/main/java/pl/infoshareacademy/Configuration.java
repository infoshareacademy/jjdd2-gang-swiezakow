package pl.infoshareacademy;

public class Configuration {
    private String filePath;
    private String linkForCPC;
    private String linkForSCC1;
    private String linkForSCC2;
    private String linkForAL;
    private int minPhraseLength;

    public Configuration() { }

    public String getLinkForSCC2() {
        return linkForSCC2;
    }

    public String getLinkForSCC1() {
        return linkForSCC1;
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
