# spring-cloud-k8s-examples

use SPRING_CONFIGURATION_NAME environment variable to change the location of application config properties file.

docker build . -t service-a:latest

docker images

docker tag <image-id> bashdocker/service-a:latest
  
docker push bashdocker/service-a:latest


docker build . -t service-b:latest

docker images

docker tag <image-id> bashdocker/service-b:latest
  
docker push bashdocker/service-b:latest


docker build . -t application-gateway:latest

docker images

docker tag <image-id> bashdocker/application-gateway:latest
  
docker push bashdocker/application-gateway:latest


kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default

kubectl create clusterrole deployment-role --verb=* --resource=deployments,deployments.apps, services

kubectl create clusterrolebinding deployment-binding --clusterrole=deployment-role --serviceaccount=default:spring-boot-service
