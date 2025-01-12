package com.home.PracticeAPI;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public class LocalstackServices {

    public static LocalStackContainer container;

    public static void start(){
        container = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3"))
                .withServices(LocalStackContainer.Service.DYNAMODB);
        container.start();
    }
}
