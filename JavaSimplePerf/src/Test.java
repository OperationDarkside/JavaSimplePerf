public class Test {
    public static void main(String[] args) throws Exception {
        
        SimplePerf.StartRecording("SimplePerfTest");
        containsSlowCode();
        SimplePerf.EndRecordingAndPrint();
    }

    private static void containsSlowCode(){
        SimplePerf.addCheckpoint("Start containsSlowCode");
        System.out.println("Printing some Text to the console");
        SimplePerf.addCheckpoint("time after sysout");
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimplePerf.addCheckpoint("End containsSlowCode");
    }
}
