chcp 1251
xcopy  /H /Y /C "c:\Users\aavhimovich\Google ����\SNM2\dist\SNM.jar" "d:\INF\123\SNM\dist"
xcopy  /H /Y /C "d:\INF\123\SNM\icon.ico" "d:\INF\123\SNM\dist"
xcopy  /H /Y /C "d:\INF\123\SNM\Launcher.xml" "d:\INF\123\SNM\dist"
xcopy  /H /Y /C "d:\INF\123\SNM\replace.bat" "d:\INF\123\SNM\dist"
xcopy  /H /Y /C "d:\INF\123\SNM\toexe.bat" "d:\INF\123\SNM\dist"

d:\Launch4j\launch4jc.exe Launcher.xml

xcopy  /H /Y /C "d:\INF\123\SNM\dist\SNM.exe" "z:\���\���������\���������\SNM"
pause