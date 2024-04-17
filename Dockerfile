FROM public.ecr.aws/amazoncorretto/amazoncorretto:21 

COPY target/pr-auto-close-*.jar /usr/local/pr-auto-close.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/pr-auto-close.jar"]

