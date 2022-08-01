import java.util.ArrayList;

public class SimplePerf {

    private static String recordingName = "Default";
    private final static ThreadLocal<ArrayList<Checkpoint>> checkPoints = ThreadLocal.withInitial(() -> new ArrayList<>());

    public static void StartRecording() {
        if (!checkPoints.get().isEmpty()) {
            System.out.println("Found old recording: " + recordingName);
            printPerfTrace();
            checkPoints.get().clear();
        }
        recordingName = "Default";
        System.out.println("Start new recording: " + recordingName);
    }

    public static void StartRecording(String newRecordingName) {
        if (!checkPoints.get().isEmpty()) {
            System.out.println("Found old recording: " + recordingName);
            printPerfTrace();
            checkPoints.get().clear();
        }
        recordingName = newRecordingName;
        System.out.println("Start new recording: " + recordingName);
    }

    public static void addCheckpoint(String name) {
        checkPoints.get().add(new Checkpoint(name));
    }

    public static void EndRecordingAndPrint() {
        System.out.println("End of recording: " + recordingName);
        printPerfTrace();
    }

    public static void clearRecording() {
        System.out.println("Clear recording: " + recordingName);
        checkPoints.get().clear();
    }

    public static String stringify() {
        if (checkPoints.get().isEmpty()) {
            return "No checkpoints found for \"" + recordingName + "\"";
        }
        if (checkPoints.get().size() == 1) {
            var cp = checkPoints.get().get(0);
            return "Only one checkpoint found for \"" + recordingName + "\".\r\n" + cp.getName() + ": "
                    + cp.getTimestamp() + ".";
        }
        System.out.println("Timings for recording: " + recordingName);
        var it = checkPoints.get().iterator();
        var prevCheckpoint = it.next();
        StringBuilder sb = new StringBuilder(prevCheckpoint.getName());
        while (it.hasNext()) {
            var currentCheckpoint = it.next();
            long msDiff = currentCheckpoint.getTimestamp() - prevCheckpoint.getTimestamp();
            sb.append("\r\n\t|\r\n\t");
            sb.append(msDiff);
            sb.append("\r\n\t|\r\n");
            sb.append(currentCheckpoint.getName());
            prevCheckpoint = currentCheckpoint;
        }
        return sb.toString();
    }

    public static void printPerfTrace() {
        System.out.println("\r\n" + stringify());
    }
}
