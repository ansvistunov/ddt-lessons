��� ������� RMI � ������ ������������ �������� �������:
1. ������ ���� ������� net.rmi.loader.server.server
2. ������ ���� ������� net.HTTPServer. �� ����� ������ �� target\classes, ������� ��� ������ ��� ���� (����� �������������� ��������� ������ mvn install)
3. ������ �������
java  -Djava.rmi.server.codebase="http://localhost:8000/"  -Djava.security.policy=security.policy    net.rmi.loader.client.Client 

����������� ������� ���� � ����� ����� ������� � java.rmi.server.codebase.
����������� ����� ��������� ���������� ��� ����������� ������� (������ ������������� � ����� security.policy)

���� ����� ������������ ������ rmiregistry, �� �� ������ ���� �����:
rmiregistry.exe -J-Djava.rmi.server.codebase=http://localhost:8000/ 8080

��� ����, ����� � ������-������� ������� ip ����, � ������� �� ����� ����������������� - ��������� �������� java.rmi.server.hostname
��������, ��� ���:
java -classpath target/classes -Djava.rmi.server.hostname=132.145.228.39 com.asw.rmi.ex1.BillingServiceImpl

