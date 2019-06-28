@ECHO OFF
ECHO Sad ce Laza da vam importuje sve nista se ne brinite!
keytool -import -noprompt -trustcacerts -alias agent -file agent.crt -keystore cacerts -storepass changeit
keytool -import -noprompt -trustcacerts -alias discovery -file discovery.crt -keystore cacerts -storepass changeit
keytool -import -noprompt -trustcacerts -alias gateway -file gateway.crt -keystore cacerts -storepass changeit
keytool -import -noprompt -trustcacerts -alias accommodation -file accommodation.crt -keystore cacerts -storepass changeit
keytool -import -noprompt -trustcacerts -alias reservation -file reservation.crt -keystore cacerts -storepass changeit
PAUSE