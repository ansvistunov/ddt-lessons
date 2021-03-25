ƒл€ запуска RMI в режиме динамической загрузки классов:
1. ƒолжен быть запущен net.rmi.loader.server.server
2. ƒолжен быть запущен net.HTTPServer. он берет классы из target\classes, поэтому они должны там быть (нужно предварительно построить проект mvn install)
3. запуск клиента
java  -Djava.rmi.server.codebase="http://localhost:8000/"  -Djava.security.policy=security.policy    net.rmi.loader.client.Client 

ќЅя«ј“≈Ћ№Ќќ ставить слэш в конце имени ресурса в java.rmi.server.codebase.
ќЅя«ј“≈Ћ№Ќќ нужно разрешать соединение дл€ загруженных классов (гранты прописываютс€ в файле security.policy)