# Сборка Maven
для сборки БЕЗ SOAP: mvn  install -P no-wss
для сборки с SOAP mvn install и при этом сервис должен быть запущен (чтобы с него можно было загрузить wsdl)



# RMI

Для запуска RMI в режиме динамической загрузки классов:
1. Должен быть запущен net.rmi.loader.server.server
2. Должен быть запущен net.HTTPServer. он берет классы из target\classes, поэтому они должны там быть (нужно предварительно построить проект mvn install)
3. запуск клиента
java  -Djava.rmi.server.codebase="http://localhost:8000/"  -Djava.security.policy=security.policy    net.rmi.loader.client.Client 

ОБЯЗАТЕЛЬНО ставить слэш в конце имени ресурса в java.rmi.server.codebase.
ОБЯЗАТЕЛЬНО нужно разрешать соединение для загруженных классов (гранты прописываются в файле security.policy)

Если нужно организовать запуск rmiregistry, то он должен быть таким:
rmiregistry.exe -J-Djava.rmi.server.codebase=http://localhost:8000/ 8080

Для того, чтобы в прокси-объекте указать ip узла, с которым он будет взаимодействовать - указываем параметр java.rmi.server.hostname
например, вот так:
java -classpath target/classes -Djava.rmi.server.hostname=132.145.228.39 com.asw.rmi.ex1.BillingServiceImpl

Kafka
Producer:
mvn exec:java -Dexec.mainClass=kafka.hello.HelloProducer -Dexec.args="admin admin-secret localhost:9092 test"
Consumer:
mvn exec:java -Dexec.mainClass=kafka.hello.HelloConsumer -Dexec.args="admin admin-secret localhost:9092 test"



