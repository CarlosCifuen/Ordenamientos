public class ResultRow {
    private final String algorithm;
    private final int n;
    private final String scenario; // "random" o "sorted"
    private final long timeNs;

    public ResultRow(String algorithm, int n, String scenario, long timeNs) {
        this.algorithm = algorithm;
        this.n = n;
        this.scenario = scenario;
        this.timeNs = timeNs;
    }

    public String getAlgorithm() { return algorithm; }
    public int getN() { return n; }
    public String getScenario() { return scenario; }
    public long getTimeNs() { return timeNs; }
}

