# spring-cloud-k8s-examples

docker build . -t service-a:latest

docker images

docker tag <image-id> bashdocker/service-a:latest
  
docker push bashdocker/service-a:latest


docker build . -t service-b:latest

docker images

docker tag <image-id> bashdocker/service-b:latest
  
docker push bashdocker/service-b:latest
