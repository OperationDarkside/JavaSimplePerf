# JavaSimplePerf
A simple static library to gather and present execution timings

The resolution is only milliseconds at the moment, but might change to nanoseconds + formatting later.

No dependencies

Example:
```Java
SimplePerf.StartRecording("SimplePerfTest");
containsSlowCode();
SimplePerf.EndRecordingAndPrint();
```
[Full code](JavaSimplePerf/src/Test.java)


Output:
```
Start new recording: SimplePerfTest
Printing some Text to the console
End of recording: SimplePerfTest
Timings for recording: SimplePerfTest

Start containsSlowCode
        |
        0
        |
time after sysout
        |
        604
        |
End containsSlowCode
```
